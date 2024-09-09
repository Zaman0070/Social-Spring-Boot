package com.social_app.social.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.social_app.social.services.FileService;
import java.io.File;

@Service
public class FileServiceImp implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        @SuppressWarnings("null")
        String filePath = uuid.concat(name.substring(name.lastIndexOf(".")));
        String fullPath = path + File.separator + filePath;

        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        Files.copy(file.getInputStream(), Paths.get(fullPath));
        return name;
    }

    

}
