package com.example.mmtr2.repository;

import com.example.mmtr2.entity.WordNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordNumberRepository extends CrudRepository<WordNumber, Long> {

    @Override
    List<WordNumber> findAll();

    @Override
    Optional<WordNumber> findById(Long aLong);

    Optional<WordNumber> findByWord(String word);

    @Override
    void deleteById(Long aLong);

    void deleteByWord(String word);

}
