package com.example.mmtr2.repository;

import com.example.mmtr2.entity.WordLatin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordLatinRepository extends CrudRepository<WordLatin, Long> {

    @Override
    List<WordLatin> findAll();

    @Override
    Optional<WordLatin> findById(Long aLong);

    Optional<WordLatin> findByWord(String word);

    @Override
    void deleteById(Long aLong);

    void deleteByWord(String word);

}
