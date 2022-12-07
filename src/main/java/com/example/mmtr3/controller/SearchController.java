package com.example.mmtr3.controller;


import com.example.mmtr3.dto.request.WordRequest;
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

    @GetMapping
    public List<WordResponse> search(@RequestBody WordRequest wordRequest){
        return searcher.searchWord(wordRequest);
    }
}
