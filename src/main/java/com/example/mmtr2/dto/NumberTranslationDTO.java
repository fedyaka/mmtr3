package com.example.mmtr2.dto;

import com.example.mmtr2.constant.ValidConstant;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Validated
public enum NumberTranslationDTO {;
    private interface Id{Long getId();}
    private interface IdWord{Long getIdWord();}
    private interface Translation{String getTranslation();}

    public enum Request{;
        @Value public static class Create implements IdWord, Translation{
            @Min(value = 0, message = ValidConstant.MIN_ID)
            Long idWord;
            @NotBlank(message = ValidConstant.NOT_BLANK)
            @Pattern(regexp = ValidConstant.NUMBER_REGEX, message = ValidConstant.NUMBER_PATTERN)
            String translation;
        }

        @Value public static class Patch implements Id, Translation{

            @Min(value = 0, message = ValidConstant.MIN_ID)
            Long id;

            @NotBlank(message = ValidConstant.NOT_BLANK)
            @Pattern(regexp = ValidConstant.NUMBER_REGEX, message = ValidConstant.NUMBER_PATTERN)
            String translation;
        }

        @Value public static class Delete implements Id{
            @Min(value = 0, message = ValidConstant.MIN_ID)
            Long id;

            @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
            public Delete(Long id){
                this.id = id;
            }
        }

    }
    public enum Response{;
        @Value public static class Public implements Id, Translation{
            Long id;
            String translation;
        }

    }

}
