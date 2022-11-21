package com.example.mmtr2.dto.request.add;

import com.example.mmtr2.constant.ValidConstant;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Value
public class AddTranslationLatin implements AddTranslation{

    @Min(value = 0, message = ValidConstant.MIN_ID)
    Long idWord;

    @NotBlank(message = ValidConstant.NOT_BLANK)
    @Pattern(regexp = ValidConstant.LATIN_REGEX, message = ValidConstant.LATIN_PATTERN)
    String translation;
}
