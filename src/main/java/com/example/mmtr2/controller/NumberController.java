package com.example.mmtr2.controller;

import com.example.mmtr2.dto.request.NumberRequest;
import com.example.mmtr2.dto.request.add.AddTranslationNumber;
import com.example.mmtr2.dto.request.patch.PatchNumber;
import com.example.mmtr2.dto.response.WordResponse;
import com.example.mmtr2.service.NumberService;
import com.example.mmtr2.service.NumberTranslateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dictionary/number")
public class NumberController {

    NumberService numberService;

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping
    public List<WordResponse> getAll(){
        return numberService.findAll();
    }

    @GetMapping("/{id}")
    public WordResponse getById(@PathVariable("id") Long id){
        return numberService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WordResponse createWord(@RequestBody NumberRequest createDto){
        return numberService.save(createDto);
    }

    @PostMapping("/translate")
    public WordResponse addTranslation(@RequestBody AddTranslationNumber translationWithIdWord){
        return numberService.addTranslation(translationWithIdWord);
    }

    @PatchMapping
    public WordResponse patchWord(@RequestBody PatchNumber patchDto){
        return numberService.patch(patchDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        numberService.deleteById(id);
    }

    @DeleteMapping
    public void deleteByWord(@RequestBody NumberRequest deleteDto){
        numberService.deleteByWord(deleteDto);
    }






    
}
