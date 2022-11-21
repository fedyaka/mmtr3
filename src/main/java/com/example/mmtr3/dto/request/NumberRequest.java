package com.example.mmtr3.dto.request;

import com.example.mmtr3.constant.ValidConstant;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Value
public class NumberRequest implements WordRequest{

    @NotBlank(message = ValidConstant.NOT_BLANK)
    @Pattern(regexp = ValidConstant.NUMBER_REGEX, message = ValidConstant.NUMBER_PATTERN)
    String word;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) // or Mode.DELEGATING
    public NumberRequest(String word){
        this.word = word;
    }
}
