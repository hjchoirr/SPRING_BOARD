package org.choongang.file.controllers;

import lombok.RequiredArgsConstructor;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.service.FileUploadService;
import org.choongang.global.rests.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileUploadService uploadService;

    @PostMapping("/upload")
    public ResponseEntity<JSONData> upload(@RequestPart("file") MultipartFile[] files,
                                          @RequestParam(name="gid", required = false) String gid,
                                          @RequestParam(name="gid", required = false) String location) {

        //gid = StringUtils.hasText(gid) ? gid : UUID.randomUUID().toString();

        List<FileInfo> items = uploadService.upload(files, gid, location);
        HttpStatus status = HttpStatus.CREATED;
        JSONData data = new JSONData(items);
        data.setStatus(status);
        return ResponseEntity.status(status).body(data);

    }

}

