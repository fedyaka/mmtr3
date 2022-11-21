package com.example.mmtr3.repository.extend;

import com.example.mmtr3.entity.LatinWord;
import com.example.mmtr3.entity.extend.AbstractWord;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface WordRepository<E extends AbstractWord> extends CommonRepository<E>{

    Optional<LatinWord> findByWord(String word);

    void deleteByWord(String word);
}
