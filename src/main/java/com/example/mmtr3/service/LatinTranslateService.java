package com.example.mmtr3.service;

import com.example.mmtr3.constant.ValidConstant;
import com.example.mmtr3.dto.response.TranslationResponse;
import com.example.mmtr3.entity.LatinTranslation;
import com.example.mmtr3.exception.MyException.TranslationNotFoundException;
import com.example.mmtr3.repository.LatinTranslationRepository;
import com.example.mmtr3.service.extend.AbstractTranslationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.List;

@Service
@Validated
public class LatinTranslateService extends AbstractTranslationService<LatinTranslation, LatinTranslationRepository> {

    LatinService latinService;

    public LatinTranslateService(LatinTranslationRepository repository, LatinService latinService) {
        super(repository);
        this.latinService = latinService;
    }

    public List<TranslationResponse> findAllByIdWord(@Min(value = 0, message = ValidConstant.MIN_ID)
                                                                     Long idWord) {
        var wordLatin = latinService.findById(idWord);
        if (wordLatin.getTranslations().isEmpty()){
            throw new TranslationNotFoundException();
        }
        return wordLatin.getTranslations();
    }
}