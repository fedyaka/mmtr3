package com.example.mmtr3.controller;

import com.example.mmtr3.dto.request.WordRequest;
import com.example.mmtr3.dto.response.WordResponse;
import com.example.mmtr3.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dictionary/word")
public class WordController {

    WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping()
    public List<WordResponse> getAllByDictionaryId(@RequestParam(name = "dictionaryId") Long id){
        return  wordService.getAllByDictionaryId(id);
    }

    @GetMapping("/{id}")
    public WordResponse getById(@PathVariable Long id){
        return wordService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WordResponse addWord(@RequestBody WordRequest wordRequest){
        return wordService.addWord(wordRequest);
    }

    @PatchMapping()
    public WordResponse patchWord(@RequestBody WordRequest wordRequest){
        return wordService.patchWord(wordRequest);
    }

    @DeleteMapping("/{id}")
    public void removeWord(@PathVariable Long id){
        wordService.removeWord(id);
    }
}
