package com.example.mmtr3.service;

import com.example.mmtr3.dto.request.add.AddTranslation;
import com.example.mmtr3.dto.response.WordResponse;
import com.example.mmtr3.entity.NumberTranslation;
import com.example.mmtr3.entity.NumberWord;
import com.example.mmtr3.exception.MyException.TranslationAlreadyExistException;
import com.example.mmtr3.exception.MyException.WordNotFoundException;
import com.example.mmtr3.repository.NumberWordRepository;
import com.example.mmtr3.service.extend.AbstractWordService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Service
@Validated
public class NumberService extends AbstractWordService<NumberWord, NumberWordRepository> {



    public NumberService(NumberWordRepository repository) {
        super(repository);
    }

    public WordResponse addTranslation(@Valid AddTranslation translationWithIdWord){
        Optional<NumberWord> word = repository.findById(translationWithIdWord.getIdWord());
        if (word.isEmpty()){
            throw new WordNotFoundException();
        }
        NumberWord wordE = word.get();
        if (wordE.getTranslations().stream()
                .anyMatch(e -> e.getTranslation().equals(translationWithIdWord.getTranslation()))){
            throw new TranslationAlreadyExistException();
        }
        wordE.getTranslations().add(new NumberTranslation(translationWithIdWord.getTranslation(), wordE));
        return ToDtoService.toDto(repository.save(wordE));
    }

    @Override
    protected NumberWord newE() {
        return new NumberWord();
    }
}
