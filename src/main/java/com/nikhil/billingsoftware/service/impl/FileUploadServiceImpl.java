package com.nikhil.billingsoftware.service.impl;

import com.nikhil.billingsoftware.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectAclResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    private final S3Client s3Client;

    @Override
    public String uploadFile(MultipartFile file) {

       String fileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
       String imgUrl = UUID.randomUUID().toString()+"."+fileName;

       try{
           PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                   .bucket(bucketName)
                   .key(imgUrl)
                   .acl("public-read")
                   .contentType(file.getContentType())
                   .build();
           PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

           if(putObjectResponse.sdkHttpResponse().isSuccessful()) {
               return "https://"+bucketName+".s3.amazonaws/"+imgUrl;
           }
           else {
               throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
           }


       }catch(IOException e){
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
       }

    }

    @Override
    public boolean deleteFile(String imgUrl) {
       String fileName = imgUrl.substring(imgUrl.lastIndexOf(".")+1);
       DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucket(bucketName).key(fileName).build();
       s3Client.deleteObject(deleteObjectRequest);
       return true;

    }
}
