package com.example.mmtr2.repository;

import com.example.mmtr2.entity.LatinTranslation;
import com.example.mmtr2.entity.LatinWord;
import com.example.mmtr2.repository.extend.TranslationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LatinTranslationRepository extends TranslationRepository<LatinTranslation> {


}
