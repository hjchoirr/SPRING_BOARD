package org.choongang.board.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.board.controllers.RequestBoardData;
import org.choongang.board.entities.Board;
import org.choongang.board.entities.BoardData;
import org.choongang.board.exceptions.BoardDataNotFoundException;
import org.choongang.board.exceptions.BoardNotFoundException;
import org.choongang.board.repositories.BoardDataRepository;
import org.choongang.board.repositories.BoardRepository;
import org.choongang.member.MemberUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
@Transactional  // board, member fetch=Lazy 이므로 영속성 유지 위해 Transactional, 안하면 못 가져올수 있다
public class BoardDataSaveService {
    private final BoardDataRepository boardDataRepository;
    private final BoardRepository boardRepository;
    private final MemberUtil memberUtil;
    private final HttpServletRequest request;
    private final PasswordEncoder encoder;
    private final FileUploadDoneService doneService;

    public BoardData save(RequestBoardData form) {
        String mode = form.getMode();
        mode = StringUtils.hasText(mode) ? mode.trim() : "write";

        String gid = form.getGid();

        BoardData data = null;
        Long seq = form.getSeq();
        if(seq != null && mode.equals("update")) { //수정
            data = boardDataRepository.findById(seq).orElseThrow(BoardDataNotFoundException::new);

        }else{ //작성
            String bId = form.getBId();
            Board board = boardRepository.findById(bId).orElseThrow(BoardNotFoundException::new);
            data = BoardData.builder()
                .gid(gid)
                .board(board)
                .member(memberUtil.getMember())
                .ip(request.getRemoteAddr())
                .ua(request.getHeader("User-Agent"))
                .build();
        }

        //수정, 작성 공통 컬럼
        data.setSubject(form.getSubject());
        data.setPoster(form.getPoster());
        data.setContent(form.getContent());
        data.setNum1(form.getNum1());
        data.setText1(form.getText1());
        data.setLongText1(form.getLongText1());

        //비회원비밀번호
        String guestPw = form.getGuestPw();
        if(StringUtils.hasText(guestPw)) {
            data.setGuestPw(encoder.encode(guestPw));
        }
        if(memberUtil.isAdmin()) {
            data.setNotice(form.isNotice());
        }

        boardDataRepository.save(data);
        //파일업로드 완료처리
        doneService.process(gid);
        return data;
    }

}
