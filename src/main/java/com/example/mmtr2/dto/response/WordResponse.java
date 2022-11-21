package com.example.mmtr2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WordResponse {
    Long id;
    String word;
    List<TranslationResponse> translations;
}
