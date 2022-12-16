package com.example.mmtr3.service;
import com.example.mmtr3.dto.RuleDto;
import com.example.mmtr3.dto.response.DictionaryResponse;
import com.example.mmtr3.dto.response.TranslationResponse;
import com.example.mmtr3.dto.response.WordResponse;
import com.example.mmtr3.entity.Dictionary;
import com.example.mmtr3.entity.Translation;
import com.example.mmtr3.entity.Word;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ToDtoService {

    public static DictionaryResponse toDto(Dictionary dictionary){
        if (dictionary.getWords() == null || dictionary.getWords().isEmpty()){
            return toDtoWithListEmpty(dictionary);
        }
        return new DictionaryResponse(
                dictionary.getId(),
                dictionary.getName(),
                new RuleDto(dictionary.getRule().getPattern(), dictionary.getRule().getDescription()),
                dictionary.getWords().stream().map(ToDtoService::toDto).collect(Collectors.toList()));
    }

    public static WordResponse toDto(Word word){
        if (word.getTranslations() == null || word.getTranslations().isEmpty()){
            return toDtoWithListEmpty(word);
        }
        return new WordResponse(word.getDictionary().getId(), word.getId(), word.getWord(),
                word.getTranslations().stream().map(ToDtoService::toDto).collect(Collectors.toList()));
    }

    public static TranslationResponse toDto(Translation translation){
        return new TranslationResponse(translation.getId(), translation.getTranslation());
    }

    public static DictionaryResponse toDtoWithListEmpty(Dictionary dictionary) {
        return new DictionaryResponse(dictionary.getId(),
                dictionary.getName(),
                new RuleDto(dictionary.getRule().getPattern(), dictionary.getRule().getDescription()),
                null);
    }

    public static WordResponse toDtoWithListEmpty(Word word) {
        return new WordResponse(word.getDictionary().getId(), word.getId(), word.getWord(), null);
    }

}
