package com.example.mmtr3.repository;

import com.example.mmtr3.entity.Translation;
import com.example.mmtr3.repository.extend.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository extends CommonRepository<Translation> {

    List<Translation> findAllByWordId(Long wordId);

    boolean existsTranslationByWordIdAndTranslation(Long wordId, String translation);



}
