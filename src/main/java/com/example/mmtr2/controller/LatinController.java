package com.example.mmtr2.controller;

import com.example.mmtr2.dto.LatinDTO;
import com.example.mmtr2.service.LatinService;
import com.example.mmtr2.service.LatinTranslateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dictionary/latin")
public class LatinController {

    LatinService dictionaryService;
    LatinTranslateService translateService;

    public LatinController(LatinService latinService, LatinTranslateService translateService) {
        this.dictionaryService = latinService;
        this.translateService = translateService;
    }

    @GetMapping
    public List<LatinDTO.Response.Public> getAll(){
        return dictionaryService.findAll();
    }

    @GetMapping("/{id}")
    public LatinDTO.Response.Public getById(@PathVariable("id") Long id){
        return dictionaryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LatinDTO.Response.Public createWord(@RequestBody LatinDTO.Request.Create createDto){
        return dictionaryService.save(createDto);
    }

    @PatchMapping
    public LatinDTO.Response.Public patchWord(@RequestBody LatinDTO.Request.Patch patchDto){
        return dictionaryService.patch(patchDto);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        dictionaryService.deleteById(id);
    }

    @DeleteMapping
    public void deleteByWord(@RequestBody LatinDTO.Request.Delete deleteDto){
        dictionaryService.deleteByWord(deleteDto);
    }






    
}
