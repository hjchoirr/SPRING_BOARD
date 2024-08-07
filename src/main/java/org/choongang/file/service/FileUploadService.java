package org.choongang.file.service;

import lombok.RequiredArgsConstructor;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.repositories.FileInfoRepository;
import org.choongang.global.configs.FileProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(FileProperties.class)
public class FileUploadService {

    private final FileInfoRepository fileInfoRepository;
    private final FileProperties properties;
    private final FileInfoService infoService;
    private final FileInfoService fileInfoService;

    public List<FileInfo> upload(MultipartFile[] files, String gid, String location) {
        /**
         * 1. 파일 정보 저장
         * 2. 파일 서버로 이동
         * 3. 이미지면 썸네일 생성
         * 4. 업로드한 파일 목록 반환
         */

        // 1. 파일 정보 저장
        gid = StringUtils.hasText(gid) ? gid : UUID.randomUUID().toString();

        List<FileInfo> uploadedFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename(); // 업로드한 파일의 원래 이름
            String contentType = file.getContentType();
            String extension = fileName.substring(fileName.lastIndexOf('.'));

            FileInfo fileInfo = FileInfo.builder()
                    .gid(gid)
                    .location(location)
                    .fileName(fileName)
                    .extension(extension)
                    .contentType(contentType)
                    .build();
            fileInfoRepository.saveAndFlush(fileInfo);

            // 2. 파일 서버로 이동
            long seq = fileInfo.getSeq();
            String uploadDir = properties.getPath() + "/" + (seq % 10L);
            File dir = new File(uploadDir);
            if(!dir.exists() || !dir.isDirectory()) {
                dir.mkdir();
            }

            String uploadPath = uploadDir + "/" + seq + extension;
            try {

                file.transferTo(new File(uploadPath));
                uploadedFiles.add(fileInfo); //업로드 성공 파일 정보

            } catch (IOException e) {
                e.printStackTrace();
                //파일 이동 실패 시 파일 정보 지우기
                fileInfoRepository.delete(fileInfo);
                fileInfoRepository.flush();
            }

            //3.
        }
        uploadedFiles.forEach(fileInfoService::addFileInfo);
        return uploadedFiles;
    }

}
