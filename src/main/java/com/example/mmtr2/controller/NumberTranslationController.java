package com.example.mmtr2.controller;

import com.example.mmtr2.dto.LatinTranslationDTO;
import com.example.mmtr2.dto.LatinDTO;
import com.example.mmtr2.dto.NumberDTO;
import com.example.mmtr2.dto.NumberTranslationDTO;
import com.example.mmtr2.service.NumberTranslateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dictionary/latin/translation")
public class NumberTranslationController {

    //Это абстракция!!!

    NumberTranslateService translateService;

    public NumberTranslationController(NumberTranslateService translateService) {
        this.translateService = translateService;
    }

    @GetMapping
    public List<NumberTranslationDTO.Response.Public> getAll(){
        return translateService.findAll();
    }

    @GetMapping("/{id}")
    public NumberTranslationDTO.Response.Public getById(@PathVariable("id") Long id){
        return translateService.findById(id);
    }

    @GetMapping("/word/{idWord}")
    public List<NumberTranslationDTO.Response.Public> getAllByIdWord(@PathVariable("idWord") Long idWord){
        return translateService.findAllByIdWord(idWord);
    }

    @PostMapping()
    public NumberDTO.Response.Public create(@RequestBody NumberTranslationDTO.Request.Create createDto){
        return translateService.create(createDto);
    }

    @PatchMapping
    public NumberTranslationDTO.Response.Public patchById(NumberTranslationDTO.Request.Patch patch){
        return translateService.patch(patch);
    }

    @DeleteMapping("/id")
    public void deleteById(@PathVariable("id") Long id){
        translateService.deleteById(id);
    }
}
