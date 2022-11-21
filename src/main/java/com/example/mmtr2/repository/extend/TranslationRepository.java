package com.example.mmtr2.repository.extend;

import com.example.mmtr2.entity.LatinTranslation;
import com.example.mmtr2.entity.extend.AbstractTranslation;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface TranslationRepository<E extends AbstractTranslation> extends CommonRepository<E>{

    Optional<E> findByTranslation(String translation);
}
