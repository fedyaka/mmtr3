package com.example.mmtr2.controller;

import com.example.mmtr2.dto.LatinDTO;
import com.example.mmtr2.dto.NumberDTO;
import com.example.mmtr2.service.NumberService;
import com.example.mmtr2.service.NumberTranslateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dictionary/number")
public class NumberController {

    NumberService dictionaryService;
    NumberTranslateService translateService;

    public NumberController(NumberService dictionaryService, NumberTranslateService translateService) {
        this.dictionaryService = dictionaryService;
        this.translateService = translateService;
    }

    @GetMapping
    public List<NumberDTO.Response.Public> getAll(){
        return dictionaryService.findAll();
    }

    @GetMapping("/{id}")
    public NumberDTO.Response.Public getById(@PathVariable("id") Long id){
        return dictionaryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NumberDTO.Response.Public createWord(@RequestBody NumberDTO.Request.Create createDto){
        return dictionaryService.save(createDto);
    }

    @PatchMapping
    public NumberDTO.Response.Public patchWord(@RequestBody NumberDTO.Request.Patch patchDto){
        return dictionaryService.patch(patchDto);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        dictionaryService.deleteById(id);
    }

    @DeleteMapping
    public void deleteByWord(@RequestBody NumberDTO.Request.Delete deleteDto){
        dictionaryService.deleteByWord(deleteDto);
    }






    
}
