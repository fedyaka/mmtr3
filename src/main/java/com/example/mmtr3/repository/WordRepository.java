package com.example.mmtr3.repository;

import com.example.mmtr3.entity.Word;
import com.example.mmtr3.repository.extend.CommonRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends CommonRepository<Word> {

    List<Word> findAllByDictionaryId(Long dictionaryId);

    List<Word> findAllByWord(String word);

    Optional<Word> findWordByWordAndDictionaryId(String word, Long dictionary_id);

    boolean existsWordByDictionaryIdAndWord(Long dictionaryId, String word);
}
