package com.example.mmtr3.repository.extend;

import com.example.mmtr3.entity.extend.AbstractTranslation;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface TranslationRepository<E extends AbstractTranslation> extends CommonRepository<E>{

    Optional<E> findByTranslation(String translation);
}
