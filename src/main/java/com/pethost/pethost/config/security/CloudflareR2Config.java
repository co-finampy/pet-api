package com.pethost.pethost.config.security;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudflareR2Config {

    @Bean
    public AmazonS3 amazonS3() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(
                "e2513d2df84f6326d07319b84464bb7b", // Access Key ID
                "sQasHAvsbeyTNOSEDDTck5Qy4UU8exlX3Lc2Eq1T" // Secret Access Key
        );

        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        "https://e2513d2df84f6326d07319b84464bb7b.r2.cloudflarestorage.com/pethost", "auto")) // Endpoint
                .withPathStyleAccessEnabled(true)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
