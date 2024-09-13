package com.proyectoGanApp.GanApp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName;
    private final String region;

    // Constructor con inyección de valores
    public S3Service(
            @Value("${aws.access.key}") String accessKey,
            @Value("${aws.secret.key}") String secretKey,
            @Value("${aws.bucket.name}") String bucketName,  // Inyectamos aquí el bucket
            @Value("${aws.region}") String region            // Inyectamos aquí la región
    ) {
        this.bucketName = bucketName;  // Inicializamos la variable de clase
        this.region = region;          // Inicializamos la variable de clase

        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);

        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();

        // Imprimir valores para debugging
        System.out.println("Access Key: " + accessKey);
        System.out.println("Secret Key: " + secretKey);
        System.out.println("Bucket Name: " + bucketName);
        System.out.println("Region: " + region);
    }

    // Método para subir el archivo a S3
    public String uploadFile(MultipartFile file) throws IOException {
        String key = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)  // Usamos la variable `bucketName` inicializada en el constructor
                    .key(key)
                    .build();

            // Subir el archivo a S3
            PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest,
                    software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));

            if (putObjectResponse.sdkHttpResponse().isSuccessful()) {
                return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
            } else {
                throw new RuntimeException("Error al subir el archivo a S3");
            }
        } catch (S3Exception e) {
            throw new RuntimeException("Error al subir el archivo a S3: " + e.getMessage(), e);
        }
    }
}
