package com.example.mmtr3.config;

import com.example.mmtr3.dto.request.DictionaryRequest;
import com.example.mmtr3.entity.Dictionary;
import com.example.mmtr3.entity.Rule;
import com.example.mmtr3.repository.DictionaryRepository;
import com.example.mmtr3.service.DictionaryService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OnApplicationStartUp {

    DictionaryRepository dictionaryRepository;
    DictionaryService dictionaryService;

    public OnApplicationStartUp(DictionaryRepository dictionaryRepository, DictionaryService dictionaryService) {
        this.dictionaryRepository = dictionaryRepository;
        this.dictionaryService = dictionaryService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationEvent() {
        if (dictionaryRepository.findAll().size() <= 0) {
            preloadData();
        }
    }

    void preloadData() {


        //"(?=.{4}$)[a-zA-Z]+$"
        DictionaryRequest request = new DictionaryRequest("Латинский словарь", "^[a-zA-Z]{4}$", "Слово должно состоять из 4-х латинских букв");
        dictionaryService.addDictionary(request);
        //"(?=.{5}$)[0-9]+$"
        DictionaryRequest request1 = new DictionaryRequest("Цифровой словарь", "^[0-9]{5}$", "Слово должно состоять только из цифр длинной 5 знаков");
        dictionaryService.addDictionary(request1);
    }
}
