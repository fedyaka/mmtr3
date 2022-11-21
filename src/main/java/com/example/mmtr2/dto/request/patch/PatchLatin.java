package com.example.mmtr2.dto.request.patch;

import com.example.mmtr2.constant.ValidConstant;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Value
public class PatchLatin implements PatchWordRequest {

    @Min(value = 0, message = ValidConstant.MIN_ID)
    public Long id;

    @NotBlank(message = ValidConstant.NOT_BLANK)
    @Pattern(regexp = ValidConstant.LATIN_REGEX, message = ValidConstant.LATIN_PATTERN)
    public String word;
}
