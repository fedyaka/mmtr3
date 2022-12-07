package com.example.mmtr3.dto.request;

import lombok.Getter;

@Getter
public class TranslationRequest {
    private Long wordId;
    private Long id;
    private String translation;
}
