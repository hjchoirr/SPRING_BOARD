package org.choongang.file.service;

import lombok.RequiredArgsConstructor;
import org.choongang.file.constants.FileStatus;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.repositories.FileInfoRepository;
import org.choongang.global.exceptions.UnAuthorizedException;
import org.choongang.member.MemberUtil;
import org.choongang.member.entities.Member;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileDeleteService {
    private final FileInfoService infoService;
    private final FileInfoRepository fileInfoRepository;
    private final MemberUtil memberUtil;

    public FileInfo delete(Long seq) {
        FileInfo data = infoService.get(seq);

        String filePath = data.getFilePath();
        String createBy = data.getCreateBy();

        Member member = memberUtil.getMember();
        if(!memberUtil.isAdmin() && StringUtils.hasText(createBy) && memberUtil.isLogin() && member.getEmail().equals(createBy)) {
            throw new UnAuthorizedException();
        }
        //파일삭제
        File file = new File(filePath);
        if(file.exists()) {
            file.delete();
        }
        //파일정보삭제
        fileInfoRepository.delete(data);
        fileInfoRepository.flush();
        return data;

    }

    public List<FileInfo> delete(String gid, String location, FileStatus status) {
        List<FileInfo> items = infoService.getList(gid, location, status);
        items.forEach(i-> delete(i.getSeq()));
        return items;
    }

    public List<FileInfo> delete(String gid, String location) {
        return delete(gid, location, FileStatus.ALL);
    }

    public List<FileInfo> delete(String gid) {
        return delete(gid, null);
    }

}
