package com.blog_application.app.BlogApplication.services.impl;

import com.blog_application.app.BlogApplication.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl  implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException{
        // Filename
        String name = file.getOriginalFilename();

        // Random name for generated file
        String randomID = UUID.randomUUID().toString();
        String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));

        // File path
        String filePath = path + File.separator + fileName;

        // Crate folder if not created
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        // File copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
       String fullPath = path + File.separator + fileName;
       InputStream inputStream = new FileInputStream(fullPath);

       // DB logic to return inputStream
        return  inputStream;
    }
}
