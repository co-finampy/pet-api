package com.pethost.pethost.controllers;

import com.pethost.pethost.dtos.UploadResponse;
import com.pethost.pethost.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/s3")
public class S3Controller {

    private final S3Service s3Service;

    @Autowired
    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    // Endpoint para upload de arquivo
    @PostMapping(value = "/upload/{bucketName}", consumes = "multipart/form-data")
    public ResponseEntity<UploadResponse> uploadFile(
            @PathVariable String bucketName,
            @RequestParam("file") MultipartFile file) {
        try {
            // Fazendo o upload do arquivo para o S3
            UploadResponse response = s3Service.uploadFile(bucketName, file);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Retorna erro em caso de falha
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UploadResponse(null, null, "Error: " + e.getMessage()));
        }
    }

    // Endpoint para download de arquivo
    @GetMapping("/download/{bucketName}/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String bucketName, @PathVariable String fileName) {
        try {
            var s3Object = s3Service.downloadFile(bucketName, fileName);
            var inputStream = s3Object.getObjectContent();
            var bytes = inputStream.readAllBytes();

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                    .body(bytes);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Endpoint para listar arquivos
    @GetMapping("/list/{bucketName}")
    public ResponseEntity<List<String>> listFiles(@PathVariable String bucketName) {
        try {
            List<String> fileNames = s3Service.listFiles(bucketName);
            return ResponseEntity.ok(fileNames);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Endpoint para excluir arquivo
    @DeleteMapping("/delete/{bucketName}/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String bucketName, @PathVariable String fileName) {
        try {
            s3Service.deleteFile(bucketName, fileName);
            return ResponseEntity.ok("Arquivo excluído com sucesso: " + fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao excluir o arquivo: " + e.getMessage());
        }
    }
}
