package com.example.mmtr2.controller;

import com.example.mmtr2.dto.LatinTranslationDTO;
import com.example.mmtr2.dto.LatinDTO;
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
    public List<LatinTranslationDTO.Response.Public> getAll(){
        return translateService.findAll();
    }

    @GetMapping("/{id}")
    public LatinTranslationDTO.Response.Public getById(@PathVariable("id") Long id){
        return translateService.findById(id);
    }

    @GetMapping("/word/{idWord}")
    public List<LatinTranslationDTO.Response.Public> getAllByIdWord(@PathVariable("idWord") Long idWord){
        return translateService.findAllByIdWord(idWord);
    }

    @PostMapping()
    public LatinDTO.Response.Public create(@RequestBody LatinTranslationDTO.Request.Create createDto){
        return translateService.create(createDto);
    }

    @PatchMapping
    public LatinTranslationDTO.Response.Public patchById(LatinTranslationDTO.Request.Patch patch){
        return translateService.patch(patch);
    }

    @DeleteMapping("/id")
    public void deleteById(@PathVariable("id") Long id){
        translateService.deleteById(id);
    }
}
