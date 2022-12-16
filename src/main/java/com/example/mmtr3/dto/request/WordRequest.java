package com.example.mmtr3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WordRequest {
    private Long dictionaryId;
    private Long id;
    private String word;
}
