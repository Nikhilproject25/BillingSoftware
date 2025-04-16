package com.nikhil.billingsoftware.controller;

import com.nikhil.billingsoftware.service.impl.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {

   private final com.nikhil.billingsoftware.service.impl.S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/test")
    public ResponseEntity<?> testS3Connection() {
        try {
            List<String> files = s3Service.listFilesInBucket();
            return ResponseEntity.ok("✅ Connected to S3! Found files: " + files);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("❌ Failed to connect to S3: " + e.getMessage());
        }
    }
}
