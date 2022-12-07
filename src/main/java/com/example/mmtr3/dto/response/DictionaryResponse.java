package com.example.mmtr3.dto.response;

import com.example.mmtr3.dto.RuleDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryResponse {
    private Long id;
    private String name;
    private RuleDto rule;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<WordResponse> words;

}
