package com.example.mmtr3.service;

import com.example.mmtr3.dto.request.WordRequest;
import com.example.mmtr3.dto.response.WordResponse;
import com.example.mmtr3.entity.Word;
import com.example.mmtr3.exception.MyException.WordNotFoundException;
import com.example.mmtr3.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    WordRepository wordRepository;

    public SearchService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<WordResponse> searchWord(WordRequest wordRequest) {
        List<Word> words = new ArrayList<>();
        if (wordRequest.getDictionaryId() == null){
            words = wordRepository.findAllByWord(wordRequest.getWord());
            if(words.isEmpty()){
                throw new WordNotFoundException();
            }
        } else {
            words.add(wordRepository.findWordByWordAndDictionaryId(wordRequest.getWord(), wordRequest.getDictionaryId())
                    .orElseThrow(WordNotFoundException::new));
        }
        return words.stream().map(ToDtoService::toDto).collect(Collectors.toList());
    }
}
