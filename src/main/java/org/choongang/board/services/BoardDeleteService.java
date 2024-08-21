package org.choongang.board.services;

import lombok.RequiredArgsConstructor;
import org.choongang.board.entities.BoardData;
import org.choongang.board.repositories.BoardDataRepository;
import org.choongang.file.service.FileDeleteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardDeleteService {
    private final BoardDataInfoService infoService;
    private final BoardDataRepository repository;
    private final FileDeleteService deleteService;

    // soft 삭제
    public BoardData delete(Long seq) {
        BoardData data = infoService.get(seq);
        data.setDeletedAt(LocalDateTime.now());
        repository.saveAndFlush(data);
        return data;
    }
    //완전삭제
    @Transactional
    public BoardData completeDelete(Long seq) {
        BoardData data = infoService.get(seq);
        String gid = data.getGid();
        deleteService.delete(gid);
        repository.delete(data);
        repository.flush();
        return data;
    }
    //soft 삭제한 거 복구
    public BoardData recover(Long seq) {
        BoardData item = infoService.get(seq);
        item.setDeletedAt(null);
        repository.saveAndFlush(item);
        return item;
    }

}
