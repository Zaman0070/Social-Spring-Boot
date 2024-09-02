package com.social_app.social.services;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface FileService {

    String uploadFile(String path,MultipartFile file) throws IOException;

}
