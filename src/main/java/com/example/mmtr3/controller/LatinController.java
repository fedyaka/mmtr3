package com.example.mmtr3.controller;

import com.example.mmtr3.dto.request.LatinRequest;
import com.example.mmtr3.dto.request.add.AddTranslationLatin;
import com.example.mmtr3.dto.request.patch.PatchLatin;
import com.example.mmtr3.dto.response.WordResponse;
import com.example.mmtr3.service.LatinService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dictionary/latin")
public class LatinController {

    LatinService latinService;

    public LatinController(LatinService latinService) {
        this.latinService = latinService;
    }

    @GetMapping
    public List<WordResponse> getAll(){
        return latinService.findAll();
    }

    @GetMapping("/{id}")
    public WordResponse getById(@PathVariable("id") Long id){
        return latinService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WordResponse createWord(@RequestBody LatinRequest createDto){
        return latinService.save(createDto);
    }

    @PostMapping("/translation")
    public WordResponse addTranslation(@RequestBody AddTranslationLatin translationWithIdWord){
        return latinService.addTranslation(translationWithIdWord);
    }

    @PatchMapping
    public WordResponse patchWord(@RequestBody PatchLatin patchDto){
        return latinService.patch(patchDto);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        latinService.deleteById(id);
    }

    @DeleteMapping
    public void deleteByWord(@RequestBody LatinRequest deleteDto){
        latinService.deleteByWord(deleteDto);
    }






    
}
