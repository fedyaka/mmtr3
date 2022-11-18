package com.example.mmtr2.service;

import com.example.mmtr2.constant.ValidConstant;
import com.example.mmtr2.dto.NumberDTO;
import com.example.mmtr2.dto.NumberTranslationDTO;
import com.example.mmtr2.entity.NumberTranslation;
import com.example.mmtr2.entity.WordNumber;
import com.example.mmtr2.exception.MyException.EmptyDictionaryListException;
import com.example.mmtr2.exception.MyException.TranslationAlreadyExistException;
import com.example.mmtr2.exception.MyException.TranslationNotFoundException;
import com.example.mmtr2.exception.MyException.WordNotFoundException;
import com.example.mmtr2.repository.NumberTranslationRepository;
import com.example.mmtr2.repository.WordNumberRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class NumberTranslateService {

    WordNumberRepository wordNumberRepository;
    NumberTranslationRepository latinTranslationRepository;

    public NumberTranslateService(WordNumberRepository wordNumberRepository, NumberTranslationRepository latinTranslationRepository) {
        this.wordNumberRepository = wordNumberRepository;
        this.latinTranslationRepository = latinTranslationRepository;
    }

    public List<NumberTranslationDTO.Response.Public> findAll() {
        List<NumberTranslation> translations = latinTranslationRepository.findAll();
        if (translations.isEmpty()){
            throw new EmptyDictionaryListException();
        }
        return translations.stream().map(ToDtoService::toDto).collect(Collectors.toList());
    }

    public NumberTranslationDTO.Response.Public findById(@Min(value = 0, message = ValidConstant.MIN_ID)
                                                        Long id) {
        Optional<NumberTranslation> translation = latinTranslationRepository.findById(id);
        if (translation.isEmpty()){
            throw new TranslationNotFoundException();
        }
        return ToDtoService.toDto(translation.get());
    }

    public List<NumberTranslationDTO.Response.Public> findAllByIdWord(@Min(value = 0, message = ValidConstant.MIN_ID)
                                                                     Long idWord) {
        Optional<WordNumber> wordNumber = wordNumberRepository.findById(idWord);
        if (wordNumber.isEmpty()){
            throw new WordNotFoundException();
        }
        if (wordNumber.get().getTranslations().isEmpty()){
            throw new TranslationNotFoundException();
        }
        return wordNumber.get().getTranslations().stream().map(ToDtoService::toDto).collect(Collectors.toList());
    }


    public NumberTranslationDTO.Response.Public patch(@Valid NumberTranslationDTO.Request.Patch patch) {
        Optional<NumberTranslation> translationOpt = latinTranslationRepository.findById(patch.getId());
        if (translationOpt.isEmpty()){
            throw new TranslationNotFoundException();
        }
        NumberTranslation translation = translationOpt.get();
        translation.setTranslation(patch.getTranslation());
        return ToDtoService.toDto(latinTranslationRepository.save(translation));
    }

    public void deleteById(@Min(value = 0, message = ValidConstant.MIN_ID)
                           Long id) {
        if (latinTranslationRepository.findById(id).isEmpty()){
            throw new TranslationNotFoundException();
        }
        latinTranslationRepository.deleteById(id);
    }

    public NumberDTO.Response.Public create(@Valid NumberTranslationDTO.Request.Create createDto) {
        Optional<WordNumber> wordLatinOpt = wordNumberRepository.findById(createDto.getIdWord());
        if (wordLatinOpt.isEmpty()){
            throw new WordNotFoundException();
        }
        if (wordLatinOpt.get().getTranslations().stream()
                        .anyMatch(p -> p.equals(createDto.getTranslation()))){
            throw new TranslationAlreadyExistException();
        }

        WordNumber wordNumber = wordLatinOpt.get();

        NumberTranslation newTranslation = new NumberTranslation(createDto.getTranslation());
        newTranslation.setWordNumber(wordNumber);
        wordNumber.getTranslations().add(newTranslation);
        return ToDtoService.toDto(wordNumberRepository.save(wordNumber));
    }
}
