package com.example.mmtr3.repository;

import com.example.mmtr3.entity.Dictionary;
import com.example.mmtr3.repository.extend.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DictionaryRepository extends CommonRepository<Dictionary> {

    Optional<Dictionary> findByName(String name);

}
