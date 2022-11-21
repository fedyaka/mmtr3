package com.example.mmtr2.service.extend;

import com.example.mmtr2.constant.ValidConstant;
import com.example.mmtr2.dto.request.patch.PatchTranslationRequest;
import com.example.mmtr2.dto.response.TranslationResponse;
import com.example.mmtr2.entity.extend.AbstractTranslation;
import com.example.mmtr2.exception.MyException.EmptyDictionaryListException;
import com.example.mmtr2.exception.MyException.TranslationNotFoundException;
import com.example.mmtr2.repository.extend.TranslationRepository;
import com.example.mmtr2.service.ToDtoService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public abstract class AbstractTranslationService<E extends AbstractTranslation, R extends TranslationRepository<E>>
        extends AbstractService<E, R>
        implements TranslateService<E>{

    public AbstractTranslationService(R repository) {
        super(repository);
    }

    public List<TranslationResponse> findAll() {
        List<E> translations = repository.findAll();
        if (translations.isEmpty()){
            throw new EmptyDictionaryListException();
        }
        return translations.stream().map(ToDtoService::toDto).collect(Collectors.toList());
    }

    public TranslationResponse findById(@Min(value = 0, message = ValidConstant.MIN_ID)
                                                        Long id) {
        Optional<E> translation = repository.findById(id);
        if (translation.isEmpty()){
            throw new TranslationNotFoundException();
        }
        return ToDtoService.toDto(translation.get());
    }

    public TranslationResponse patch(@Valid PatchTranslationRequest patch) {
        Optional<E> translationOpt = repository.findById(patch.getId());
        if (translationOpt.isEmpty()){
            throw new TranslationNotFoundException();
        }
        E translation = translationOpt.get();
        translation.setTranslation(patch.getTranslation());
        return ToDtoService.toDto(repository.save(translation));
    }

    @Transactional
    public void deleteById(@Min(value = 0, message = ValidConstant.MIN_ID)
                           Long id) {
        if (repository.findById(id).isEmpty()){
            throw new TranslationNotFoundException();
        }
        repository.deleteById(id);
    }
}
