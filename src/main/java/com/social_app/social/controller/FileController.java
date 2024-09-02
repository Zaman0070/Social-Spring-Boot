package com.social_app.social.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.social_app.social.payloads.FileResponse;
import com.social_app.social.services.FileService;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("image") MultipartFile image) {
        String fileName = null;
        try {
            fileName = fileService.uploadFile(path, image);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null, "File uploaded failed"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new FileResponse(fileName, "File uploaded successfully"), HttpStatus.OK);

    }

}
