package com.example.mmtr2.repository;

import com.example.mmtr2.entity.LatinTranslation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LatinTranslationRepository extends CrudRepository<LatinTranslation, Long> {

    @Override
    List<LatinTranslation> findAll();

    @Override
    Optional<LatinTranslation> findById(Long aLong);

    Optional<LatinTranslation> findByTranslation(String translation);

}
