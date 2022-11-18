package com.example.mmtr2.service;

import com.example.mmtr2.dto.LatinDTO;
import com.example.mmtr2.dto.LatinTranslationDTO;
import com.example.mmtr2.dto.NumberDTO;
import com.example.mmtr2.dto.NumberTranslationDTO;
import com.example.mmtr2.entity.NumberTranslation;
import com.example.mmtr2.entity.WordLatin;
import com.example.mmtr2.entity.LatinTranslation;
//import com.example.mmtr2.entity.WordNumber;
//import com.example.mmtr2.entity.WordNumberTranslation;
import com.example.mmtr2.entity.WordNumber;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDtoService {

    public static LatinDTO.Response.Public toDto(WordLatin wordLatin){
        if (wordLatin.getTranslations() == null){
            return new LatinDTO.Response.Public(wordLatin.getId(), wordLatin.getWord(), null);
        }
        List<LatinTranslationDTO.Response.Public> translations = wordLatin.getTranslations().stream()
                .map(ToDtoService::toDto)
                .collect(Collectors.toList());
        return new LatinDTO.Response.Public(wordLatin.getId(), wordLatin.getWord(), translations);
    }

    public static LatinTranslationDTO.Response.Public toDto(LatinTranslation latinTranslation){
        return new LatinTranslationDTO.Response.Public(latinTranslation.getId(), latinTranslation.getTranslation());
    }

    public static NumberDTO.Response.Public toDto(WordNumber wordNumber){
        if (wordNumber.getTranslations() == null){
            return new NumberDTO.Response.Public(wordNumber.getId(), wordNumber.getWord(), null);
        }
        List<NumberTranslationDTO.Response.Public> translations = wordNumber.getTranslations().stream()
                .map(ToDtoService::toDto)
                .collect(Collectors.toList());
        return new NumberDTO.Response.Public(wordNumber.getId(), wordNumber.getWord(), translations);
    }

    public static NumberTranslationDTO.Response.Public toDto(NumberTranslation numberTranslation){
        return new NumberTranslationDTO.Response.Public(numberTranslation.getId(), numberTranslation.getTranslation());
    }
}
