package com.example.mmtr2.dto.request;

import com.example.mmtr2.constant.ValidConstant;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Value
public class LatinRequest implements WordRequest{

    @NotBlank(message = ValidConstant.NOT_BLANK)
    @Pattern(regexp = ValidConstant.LATIN_REGEX, message = ValidConstant.LATIN_PATTERN)
    String word;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) // or Mode.DELEGATING
    public LatinRequest(String word){
        this.word = word;
    }
}
