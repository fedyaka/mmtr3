package com.example.mmtr3.repository;

import com.example.mmtr3.entity.NumberTranslation;
import com.example.mmtr3.repository.extend.TranslationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberTranslationRepository extends TranslationRepository<NumberTranslation> {

}
