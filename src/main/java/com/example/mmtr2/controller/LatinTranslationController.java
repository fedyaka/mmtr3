package com.example.mmtr2.controller;

import com.example.mmtr2.dto.request.patch.PatchLatinTranslation;
import com.example.mmtr2.dto.response.TranslationResponse;
import com.example.mmtr2.service.LatinTranslateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dictionary/latin/translation")
public class LatinTranslationController {

    LatinTranslateService translateService;

    public LatinTranslationController(LatinTranslateService translateService) {
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
    public TranslationResponse patchById(PatchLatinTranslation patch){
        return translateService.patch(patch);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        translateService.deleteById(id);
    }
}
