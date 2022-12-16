package com.example.mmtr3.service;

import com.example.mmtr3.dto.request.TranslationRequest;
import com.example.mmtr3.dto.request.WordRequest;
import com.example.mmtr3.dto.response.TranslationResponse;
import com.example.mmtr3.entity.Dictionary;
import com.example.mmtr3.entity.Rule;
import com.example.mmtr3.entity.Translation;
import com.example.mmtr3.entity.Word;
import com.example.mmtr3.exception.MyException.*;
import com.example.mmtr3.repository.TranslationRepository;
import com.example.mmtr3.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranslationService {

    TranslationRepository translationRepository;
    WordRepository wordRepository;

    public TranslationService(TranslationRepository translationRepository, WordRepository wordRepository) {
        this.translationRepository = translationRepository;
        this.wordRepository = wordRepository;
    }

    public TranslationResponse getById(Long id) {
        return ToDtoService.toDto(translationRepository.findById(id).orElseThrow(TranslationNotFoundException::new));
    }

    public List<TranslationResponse> getAllByWordId(Long wordId) {
        List<Translation> translations = translationRepository.findAllByWordId(wordId);
        if (translations.isEmpty()){
            throw new TranslationNotFoundException();
        }
        return translations.stream().map(ToDtoService::toDto).collect(Collectors.toList());
    }

    public TranslationResponse addTranslation(TranslationRequest translationRequest) {
        Word word = wordRepository.findById(translationRequest.getWordId()).orElseThrow(WordNotFoundException::new);
        Rule rule = word.getDictionary().getRule();
        checkByRule(rule, translationRequest);

        if (translationRepository.existsTranslationByWordIdAndTranslation(word.getId(), translationRequest.getTranslation())){
            throw new TranslationAlreadyExistException();
        }

        Translation translation = new Translation();
        translation.setTranslation(translationRequest.getTranslation());
        translation.setWord(word);
        return ToDtoService.toDto(translationRepository.save(translation));
    }

    public TranslationResponse patchTranslation(TranslationRequest translationRequest){
        Translation translation = translationRepository.findById(translationRequest.getId()).orElseThrow(TranslationNotFoundException::new);

        checkByRule(translation.getWord().getDictionary().getRule(), translationRequest);
        if (translationRepository.existsTranslationByWordIdAndTranslation(translation.getWord().getId() ,translationRequest.getTranslation())){
            throw new TranslationAlreadyExistException();
        }

        translation.setTranslation(translationRequest.getTranslation());
        return ToDtoService.toDto(translationRepository.save(translation));
    }

    public void deleteTranslation(Long id) {
        translationRepository.deleteById(id);
    }

    private void checkByRule(Rule rule, TranslationRequest translationRequest){
        if (!translationRequest.getTranslation().matches(rule.getPattern())){
            throw new WordRuleException();
        }
    }
}
