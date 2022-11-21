package com.example.mmtr3.dto.request.add;

import com.example.mmtr3.constant.ValidConstant;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Value
public class AddTranslationNumber implements AddTranslation{

    @Min(value = 0, message = ValidConstant.MIN_ID)
    Long idWord;

    @NotBlank(message = ValidConstant.NOT_BLANK)
    @Pattern(regexp = ValidConstant.NUMBER_REGEX, message = ValidConstant.NUMBER_PATTERN)
    String translation;
}
