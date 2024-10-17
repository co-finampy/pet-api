package com.pethost.pethost.controllers;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
public class FileUploadController {

    @Autowired
    private AmazonS3 amazonS3;

    private final String BUCKET_NAME = "pethost"; // O nome do seu bucket no R2

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("fotoPerfil") MultipartFile file) {
        String fileUrl;
        try {
            String fileName = "perfil/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(BUCKET_NAME, fileName, inputStream, metadata);
            fileUrl = amazonS3.getUrl(BUCKET_NAME, fileName).toString();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao fazer upload do arquivo");
        }
        return ResponseEntity.ok(fileUrl);
    }
}
