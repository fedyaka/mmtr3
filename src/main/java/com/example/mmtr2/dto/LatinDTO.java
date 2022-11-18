package com.example.mmtr2.dto;

import com.example.mmtr2.constant.ValidConstant;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
public enum LatinDTO {;
    private interface Id{ Long getId();}
    private interface Word{ String getWord();}
    private interface Translations{ List<LatinTranslationDTO.Response.Public> getTranslations();}

    public enum Request{;

        @Value public static class Create implements Word {
            @NotBlank(message = ValidConstant.NOT_BLANK)
            @Pattern(regexp = ValidConstant.LATIN_REGEX, message = ValidConstant.LATIN_PATTERN)
            String word;

            //Used if the object has only one variable
            @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) // or Mode.DELEGATING
            public Create(String word){
                this.word = word;
            }
        }

        @Value public static class Delete implements Word {
            @NotBlank(message = ValidConstant.NOT_BLANK)
            @Pattern(regexp = ValidConstant.LATIN_REGEX, message = ValidConstant.LATIN_PATTERN)
            String word;

            @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) // or Mode.DELEGATING
            public Delete(String word){
                this.word = word;
            }
        }

        @Value public static class Patch implements Id, Word {
            @Min(value = 0, message = ValidConstant.MIN_ID)
            Long id;
            @NotBlank(message = ValidConstant.NOT_BLANK)
            @Pattern(regexp = ValidConstant.LATIN_REGEX, message = ValidConstant.LATIN_PATTERN)
            String word;
        }
    }

    public enum Response{;

        @Value public static class Public implements Id, Word, Translations {
            Long id;
            String word;
            List<LatinTranslationDTO.Response.Public> translations;
        }
    }

}
