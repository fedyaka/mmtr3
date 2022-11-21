package com.example.mmtr2.repository;

import com.example.mmtr2.entity.LatinWord;
import com.example.mmtr2.repository.extend.WordRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LatinWordRepository extends WordRepository<LatinWord> {
}
