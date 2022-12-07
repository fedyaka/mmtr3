package com.example.mmtr3.controller;

import com.example.mmtr3.dto.request.TranslationRequest;
import com.example.mmtr3.dto.response.TranslationResponse;
import com.example.mmtr3.service.TranslationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dictionary/word/translation")
@CrossOrigin(origins = "*")
public class TranslationController {

    TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @GetMapping("/{id}")
    public TranslationResponse getById(@PathVariable Long id){
        return translationService.getById(id);
    }

    @GetMapping
    public List<TranslationResponse> getAllByWordId(@RequestParam("wordId") Long wordId){
        return translationService.getAllByWordId(wordId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TranslationResponse addTranslation(@RequestBody TranslationRequest translationRequest){
        return translationService.addTranslation(translationRequest);
    }

    @PatchMapping
    public TranslationResponse patchTranslation(@RequestBody TranslationRequest translationRequest){
        return translationService.patchTranslation(translationRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteTranslation(@PathVariable Long id){
        translationService.deleteTranslation(id);
    }
}
