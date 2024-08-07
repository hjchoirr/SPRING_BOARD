package org.choongang.file.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.exceptions.FileNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class FileDownloadService {
    private final FileInfoService infoService;
    private final HttpServletResponse response;

    public void download(Long seq) {
        //고려사항

        //브라우저 만료시간
        //캐시 안 하도록
        //파일명 인코딩 2바이트 : 윈도우
        FileInfo data = infoService.get(seq);
        String filePath = data.getFilePath();
        String fileName = new String(data.getFileName().getBytes(), StandardCharsets.ISO_8859_1);  //파일명처리

        File file = new File(filePath);
        if(!file.exists()) {
            throw new FileNotFoundException();
        }

        String contentType = data.getContentType();
        contentType = StringUtils.hasText(contentType) ? contentType : "application/octet-stream";

        try(FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis)) {
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName );
            response.setContentType(contentType);
            response.setIntHeader("Expires", 0);
            response.setHeader("Cache-Control", "must-revalidate");
            response.setContentLengthLong(file.length());

            OutputStream out = response.getOutputStream();
            out.write(bis.readAllBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
