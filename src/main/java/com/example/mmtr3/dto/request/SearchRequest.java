package com.example.mmtr3.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class SearchRequest {
    String word;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SearchRequest(String word) {
        this.word = word;
    }
}
