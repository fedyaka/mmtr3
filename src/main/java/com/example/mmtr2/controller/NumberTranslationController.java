package com.example.mmtr2.controller;

import com.example.mmtr2.dto.request.patch.PatchLatin;
import com.example.mmtr2.dto.request.patch.PatchNumberTranslation;
import com.example.mmtr2.dto.response.TranslationResponse;
import com.example.mmtr2.service.NumberTranslateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dictionary/number/translation")
public class NumberTranslationController {

    NumberTranslateService translateService;

    public NumberTranslationController(NumberTranslateService translateService) {
        this.translateService = translateService;
    }

    @GetMapping
    public List<TranslationResponse> getAll(){
        return translateService.findAll();
    }

    @GetMapping("/{id}")
    public TranslationResponse getById(@PathVariable("id") Long id){
        return translateService.findById(id);
    }

    @GetMapping("/word/{idWord}")
    public List<TranslationResponse> getAllByIdWord(@PathVariable("idWord") Long idWord){
        return translateService.findAllByIdWord(idWord);
    }

    @PatchMapping
    public TranslationResponse patchById(PatchNumberTranslation patch){
        return translateService.patch(patch);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        translateService.deleteById(id);
    }
}
