package com.nikhil.billingsoftware.io;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    private String name;
    private String description;
    private String bgColor;

}
