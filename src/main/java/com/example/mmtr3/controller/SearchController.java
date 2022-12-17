package com.example.mmtr3.controller;


import com.example.mmtr3.dto.request.SearchRequest;
import com.example.mmtr3.dto.response.WordResponse;
import com.example.mmtr3.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("dictionary/search")
@CrossOrigin(origins = "*")
@RestController
public class SearchController {

    SearchService searcher;

    public SearchController(SearchService searcher) {
        this.searcher = searcher;
    }

    @PostMapping
    public List<WordResponse> search(@RequestBody SearchRequest searchRequest){
        return searcher.searchWord(searchRequest);
    }
}
