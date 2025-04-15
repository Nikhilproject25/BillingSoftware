package com.nikhil.billingsoftware.io;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class CategoryResponse {

    private String name;
    private String description;
    private String bgColor;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String categoryId;
    private String imageUrl;
}
