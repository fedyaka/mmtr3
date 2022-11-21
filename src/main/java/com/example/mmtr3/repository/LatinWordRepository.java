package com.example.mmtr3.repository;

import com.example.mmtr3.entity.LatinWord;
import com.example.mmtr3.repository.extend.WordRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LatinWordRepository extends WordRepository<LatinWord> {
}
