package com.example.mmtr2.repository;

import com.example.mmtr2.entity.NumberTranslation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface NumberTranslationRepository extends CrudRepository<NumberTranslation, Long> {

    @Override
    List<NumberTranslation> findAll();

    @Override
    Optional<NumberTranslation> findById(Long aLong);

    Optional<NumberTranslation> findByTranslation(String translation);

}
