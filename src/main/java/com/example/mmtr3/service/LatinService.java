package com.example.mmtr3.service;

import com.example.mmtr3.dto.request.add.AddTranslation;
import com.example.mmtr3.dto.response.WordResponse;
import com.example.mmtr3.entity.LatinTranslation;
import com.example.mmtr3.entity.LatinWord;
import com.example.mmtr3.exception.MyException.TranslationAlreadyExistException;
import com.example.mmtr3.exception.MyException.WordNotFoundException;
import com.example.mmtr3.repository.LatinWordRepository;
import com.example.mmtr3.service.extend.AbstractWordService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Service
@Validated
public class LatinService extends AbstractWordService<LatinWord, LatinWordRepository> {


    public LatinService(LatinWordRepository repository){
        super(repository);
    }

    public WordResponse addTranslation(@Valid AddTranslation translationWithIdWord){
        Optional<LatinWord> word = repository.findById(translationWithIdWord.getIdWord());
        if (word.isEmpty()){
            throw new WordNotFoundException();
        }
        LatinWord wordE = word.get();
        if (wordE.getTranslations().stream()
                .anyMatch(e -> e.getTranslation().equals(translationWithIdWord.getTranslation()))){
            throw new TranslationAlreadyExistException();
        }
        wordE.getTranslations().add(new LatinTranslation(translationWithIdWord.getTranslation(), wordE));
        return ToDtoService.toDto(repository.save(wordE));
    }

    @Override
    protected LatinWord newE() {
        return new LatinWord();
    }
}