package com.example.mmtr2.service;

import com.example.mmtr2.dto.request.SearchRequest;
import com.example.mmtr2.dto.response.WordResponse;
import com.example.mmtr2.entity.extend.AbstractWord;
import com.example.mmtr2.exception.MyException.WordNotFoundException;
import com.example.mmtr2.repository.extend.WordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    List<WordRepository> repositories;

    public SearchService(List<WordRepository> repositories) {
        this.repositories = repositories;
    }

    public List<WordResponse> searchWord(SearchRequest request){

        List<AbstractWord> list = new ArrayList<>();
        for (WordRepository<AbstractWord> repository : repositories){
            repository.findByWord(request.getWord()).ifPresent(list::add);
        }

        List<WordResponse> result = new ArrayList<>();
        for (AbstractWord word1 : list){
            result.add(ToDtoService.toDto(word1));
        }

        if (result.isEmpty()){
            throw new WordNotFoundException();
        }
        for (WordResponse word : result){
            System.out.println("ответ " + word.getWord());
        }
        return result;
    }
}
