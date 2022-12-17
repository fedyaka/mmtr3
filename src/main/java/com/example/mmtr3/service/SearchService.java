package com.example.mmtr3.service;

import com.example.mmtr3.dto.request.SearchRequest;
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

    public List<WordResponse> searchWord(SearchRequest searchRequest) {
        List<Word> words = new ArrayList<>();
        if (searchRequest.getDictionaryId() == null){
            words = wordRepository.findAllByWord(searchRequest.getWord());
            if(words.isEmpty()){
                throw new WordNotFoundException();
            }
        } else {
            words.add(wordRepository.findWordByWordAndDictionaryId(searchRequest.getWord(), searchRequest.getDictionaryId())
                    .orElseThrow(WordNotFoundException::new));
        }
        return words.stream().map(ToDtoService::toDto).collect(Collectors.toList());
    }
}
