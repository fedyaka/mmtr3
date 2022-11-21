package com.example.mmtr2.repository;

import com.example.mmtr2.entity.NumberWord;
import com.example.mmtr2.repository.extend.WordRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberWordRepository extends WordRepository<NumberWord> {

}
