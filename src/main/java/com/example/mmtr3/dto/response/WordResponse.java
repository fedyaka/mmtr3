package com.example.mmtr3.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WordResponse {
    private Long id;
    private String word;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<TranslationResponse> translations;
}
