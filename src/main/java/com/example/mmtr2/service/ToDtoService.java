package com.example.mmtr2.service;
import com.example.mmtr2.dto.response.TranslationResponse;
import com.example.mmtr2.dto.response.WordResponse;
import com.example.mmtr2.entity.LatinWord;
import com.example.mmtr2.entity.LatinTranslation;
import com.example.mmtr2.entity.extend.AbstractTranslation;
import com.example.mmtr2.entity.extend.AbstractWord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDtoService {

    public static WordResponse toDto(AbstractWord word){
        LatinWord latinWord = (LatinWord) word;
        if (latinWord.getTranslations() == null){
            return new WordResponse(latinWord.getId(), latinWord.getWord(), null);
        }
        List<TranslationResponse> translations = latinWord.getTranslations().stream()
                .map(ToDtoService::toDto)
                .collect(Collectors.toList());
        return new WordResponse(latinWord.getId(), latinWord.getWord(), translations);
    }

    public static TranslationResponse toDto(AbstractTranslation translation){
        return toDto((LatinTranslation) translation);
    }

    private static TranslationResponse toDto(LatinTranslation latinTranslation){
        return new TranslationResponse(latinTranslation.getId(), latinTranslation.getTranslation());
    }
}
