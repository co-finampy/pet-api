package com.pethost.pethost.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3Service {

    private final AmazonS3 s3Client;

    public S3Service() {
        // Certifique-se de substituir "us-east-2" pela região correta do seu bucket
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("us-east-2") // Defina a região aqui
                .build();
    }

    public String uploadFile(String bucketName, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize()); // Definindo o tamanho do arquivo
        // Criando a solicitação de upload
        PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata);
        s3Client.putObject(request); // Fazendo o upload do arquivo

        return fileName; // Retornando o nome do arquivo
    }

    public S3Object downloadFile(String bucketName, String fileName) {
        return s3Client.getObject(bucketName, fileName); // Obtendo o arquivo do S3
    }

    public List<String> listFiles(String bucketName) {
        // Listando os objetos no bucket
        ListObjectsV2Result result = s3Client.listObjectsV2(bucketName);
        return result.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey) // Obtendo o nome do arquivo
                .collect(Collectors.toList());
    }

    public void deleteFile(String bucketName, String fileName) {
        // Excluindo o arquivo do bucket
        s3Client.deleteObject(bucketName, fileName);
    }
}
