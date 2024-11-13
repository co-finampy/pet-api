package com.pethost.pethost.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.pethost.pethost.dtos.UploadResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3Service {

    private final AmazonS3 s3Client;

    public S3Service() {
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("us-east-2")
                .build();
    }

    public UploadResponse uploadFile(String bucketName, MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());

            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata);
            s3Client.putObject(request);

            String fileUrl = s3Client.getUrl(bucketName, fileName).toString();

            return new UploadResponse(fileName, fileUrl, "success");
        } catch (IOException e) {
            return new UploadResponse(null, null, "error: " + e.getMessage());
        }
    }

    public S3Object downloadFile(String bucketName, String fileName) {
        return s3Client.getObject(bucketName, fileName);
    }

    public List<String> listFiles(String bucketName) {
        ListObjectsV2Result result = s3Client.listObjectsV2(bucketName);
        return result.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }

    public void deleteFile(String bucketName, String fileName) {
        s3Client.deleteObject(bucketName, fileName);
    }
}
