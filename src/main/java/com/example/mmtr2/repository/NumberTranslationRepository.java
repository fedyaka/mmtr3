package com.example.mmtr2.repository;

import com.example.mmtr2.entity.NumberTranslation;
import com.example.mmtr2.repository.extend.TranslationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberTranslationRepository extends TranslationRepository<NumberTranslation> {

}
