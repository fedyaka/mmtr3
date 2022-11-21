package com.example.mmtr3.repository;

import com.example.mmtr3.entity.NumberWord;
import com.example.mmtr3.repository.extend.WordRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberWordRepository extends WordRepository<NumberWord> {

}
