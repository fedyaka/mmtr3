package com.example.mmtr3.service;

import com.example.mmtr3.dto.request.DictionaryRequest;
import com.example.mmtr3.dto.response.DictionaryResponse;
import com.example.mmtr3.entity.Dictionary;
import com.example.mmtr3.entity.Rule;
import com.example.mmtr3.exception.MyException.DictionaryAlreadyExistException;
import com.example.mmtr3.exception.MyException.DictionaryNotFoundException;
import com.example.mmtr3.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryService {

    DictionaryRepository dictionaryRepository;

    public DictionaryService(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public List<DictionaryResponse> getAll() {
        List<Dictionary> dictionaries = dictionaryRepository.findAll();
        if (dictionaries.isEmpty()){
            throw new DictionaryNotFoundException();
        }
        return dictionaries.stream().map(ToDtoService::toDtoWithListEmpty).collect(Collectors.toList());
    }

    public DictionaryResponse getById(Long id) {
        Dictionary dictionary = dictionaryRepository.findById(id).orElseThrow(DictionaryNotFoundException::new);
        return ToDtoService.toDto(dictionary);
    }

    public DictionaryResponse addDictionary(DictionaryRequest dictionaryRequest) {
        if (dictionaryRepository.findByName(dictionaryRequest.getName()).isPresent()){
            throw new DictionaryAlreadyExistException();
        }

        Dictionary dictionary = new Dictionary();
        dictionary.setName(dictionaryRequest.getName());
        dictionary.setRule(new Rule(dictionaryRequest.getRule().getPattern(), dictionaryRequest.getRule().getDescription()));

        return ToDtoService.toDtoWithListEmpty(dictionaryRepository.save(dictionary));
    }

    public void removeDictionaryById(Long id) {
        dictionaryRepository.deleteById(id);
    }
}
