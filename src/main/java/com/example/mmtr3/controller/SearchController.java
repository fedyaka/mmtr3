package com.example.mmtr3.controller;


import com.example.mmtr3.dto.request.SearchRequest;
import com.example.mmtr3.dto.response.WordResponse;
import com.example.mmtr3.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("dictionary/search")
@RestController
public class SearchController {

    SearchService searcher;

    public SearchController(SearchService searcher) {
        this.searcher = searcher;
    }

    @GetMapping
    public List<WordResponse> search(@RequestBody SearchRequest searchRequest){
        return searcher.searchWord(searchRequest);
    }
}