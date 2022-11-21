package com.example.mmtr2.service;

import com.example.mmtr2.constant.ValidConstant;
import com.example.mmtr2.dto.response.TranslationResponse;
import com.example.mmtr2.entity.NumberTranslation;
import com.example.mmtr2.exception.MyException.TranslationNotFoundException;
import com.example.mmtr2.repository.NumberTranslationRepository;
import com.example.mmtr2.service.extend.AbstractTranslationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.List;

@Service
@Validated
public class NumberTranslateService extends AbstractTranslationService<NumberTranslation, NumberTranslationRepository> {

    NumberService numberService;

    public NumberTranslateService(NumberTranslationRepository repository, NumberService numberService) {
        super(repository);
        this.numberService = numberService;
    }



    public List<TranslationResponse> findAllByIdWord(@Min(value = 0, message = ValidConstant.MIN_ID)
                                                     Long idWord) {
        var wordLatin = numberService.findById(idWord);
        if (wordLatin.getTranslations().isEmpty()){
            throw new TranslationNotFoundException();
        }
        return wordLatin.getTranslations();
    }

}
