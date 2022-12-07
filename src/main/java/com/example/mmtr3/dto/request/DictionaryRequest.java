package com.example.mmtr3.dto.request;

import com.example.mmtr3.dto.RuleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DictionaryRequest {
    private String name;
    private RuleDto rule;

    public DictionaryRequest(String name, String pattern, String patternDescription) {
        this.name = name;
        this.rule = new RuleDto(pattern, patternDescription);
    }
}
