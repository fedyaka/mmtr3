package com.example.mmtr2.service;

import com.example.mmtr2.constant.ValidConstant;
import com.example.mmtr2.dto.LatinTranslationDTO;
import com.example.mmtr2.dto.LatinDTO;
import com.example.mmtr2.entity.WordLatin;
import com.example.mmtr2.entity.LatinTranslation;
import com.example.mmtr2.exception.MyException.EmptyDictionaryListException;
import com.example.mmtr2.exception.MyException.TranslationAlreadyExistException;
import com.example.mmtr2.exception.MyException.TranslationNotFoundException;
import com.example.mmtr2.exception.MyException.WordNotFoundException;
import com.example.mmtr2.repository.WordLatinRepository;
import com.example.mmtr2.repository.LatinTranslationRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class LatinTranslateService {

    WordLatinRepository wordLatinRepository;
    LatinTranslationRepository latinTranslationRepository;

    public LatinTranslateService(WordLatinRepository wordLatinRepository, LatinTranslationRepository latinTranslationRepository) {
        this.wordLatinRepository = wordLatinRepository;
        this.latinTranslationRepository = latinTranslationRepository;
    }

    public List<LatinTranslationDTO.Response.Public> findAll() {
        List<LatinTranslation> translations = latinTranslationRepository.findAll();
        if (translations.isEmpty()){
            throw new EmptyDictionaryListException();
        }
        return translations.stream().map(ToDtoService::toDto).collect(Collectors.toList());
    }

    public LatinTranslationDTO.Response.Public findById(@Min(value = 0, message = ValidConstant.MIN_ID)
                                                        Long id) {
        Optional<LatinTranslation> translation = latinTranslationRepository.findById(id);
        if (translation.isEmpty()){
            throw new TranslationNotFoundException();
        }
        return ToDtoService.toDto(translation.get());
    }

    public List<LatinTranslationDTO.Response.Public> findAllByIdWord(@Min(value = 0, message = ValidConstant.MIN_ID)
                                                                     Long idWord) {
        Optional<WordLatin> wordLatin = wordLatinRepository.findById(idWord);
        if (wordLatin.isEmpty()){
            throw new WordNotFoundException();
        }
        if (wordLatin.get().getTranslations().isEmpty()){
            throw new TranslationNotFoundException();
        }
        return wordLatin.get().getTranslations().stream().map(ToDtoService::toDto).collect(Collectors.toList());
    }


    public LatinTranslationDTO.Response.Public patch(@Valid LatinTranslationDTO.Request.Patch patch) {
        Optional<LatinTranslation> translationOpt = latinTranslationRepository.findById(patch.getId());
        if (translationOpt.isEmpty()){
            throw new TranslationNotFoundException();
        }
        LatinTranslation translation = translationOpt.get();
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

    public LatinDTO.Response.Public create(@Valid LatinTranslationDTO.Request.Create createDto) {
        Optional<WordLatin> wordLatinOpt = wordLatinRepository.findById(createDto.getIdWord());
        if (wordLatinOpt.isEmpty()){
            throw new WordNotFoundException();
        }
        if (wordLatinOpt.get().getTranslations().stream()
                        .anyMatch(p -> p.equals(createDto.getTranslation()))){
            throw new TranslationAlreadyExistException();
        }

        WordLatin wordLatin = wordLatinOpt.get();

        LatinTranslation newTranslation = new LatinTranslation(createDto.getTranslation());
        newTranslation.setWordLatin(wordLatin);
//        LatinTranslation result = latinTranslationRepository.save(newTranslation);
        wordLatin.getTranslations().add(newTranslation);
        return ToDtoService.toDto(wordLatinRepository.save(wordLatin));
    }
}
