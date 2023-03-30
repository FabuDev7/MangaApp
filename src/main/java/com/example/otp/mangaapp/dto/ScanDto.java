package com.example.otp.mangaapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScanDto {

    private String title;
    private String content;
}
