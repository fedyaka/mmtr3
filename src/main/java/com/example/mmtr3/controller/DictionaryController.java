package com.example.mmtr3.controller;

import com.example.mmtr3.dto.request.DictionaryRequest;
import com.example.mmtr3.dto.response.DictionaryResponse;
import com.example.mmtr3.exception.MyException.DictionaryNotFoundException;
import com.example.mmtr3.service.DictionaryService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping()
    public List<DictionaryResponse> getAll(){
        return dictionaryService.getAll();
    }

    @GetMapping("/{id}")
    public DictionaryResponse getById(@PathVariable Long id){
        return dictionaryService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public DictionaryResponse addDictionary(@RequestBody DictionaryRequest dictionaryRequest){
        return dictionaryService.addDictionary(dictionaryRequest);
    }

    @DeleteMapping("/{id}")
    public void removeDictionaryById(@PathVariable Long id){
        try{
            dictionaryService.removeDictionaryById(id);
        }catch (EmptyResultDataAccessException e){
            throw new DictionaryNotFoundException();
        }
    }

}
