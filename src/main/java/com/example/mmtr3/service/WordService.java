package com.example.mmtr3.service;

import com.example.mmtr3.dto.request.WordRequest;
import com.example.mmtr3.dto.response.WordResponse;
import com.example.mmtr3.entity.Dictionary;
import com.example.mmtr3.entity.Rule;
import com.example.mmtr3.entity.Word;
import com.example.mmtr3.exception.MyException.*;
import com.example.mmtr3.repository.DictionaryRepository;
import com.example.mmtr3.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {

    DictionaryRepository dictionaryRepository;
    WordRepository wordRepository;

    public WordService(DictionaryRepository dictionaryRepository, WordRepository wordRepository) {
        this.dictionaryRepository = dictionaryRepository;
        this.wordRepository = wordRepository;
    }

    public List<WordResponse> getAllByDictionaryId(Long id) {
        List<Word> words = wordRepository.findAllByDictionaryId(id);
        if (words.isEmpty()){
            throw new DictionaryIsEmptyException();
        }
        return words.stream().map(ToDtoService::toDto).collect(Collectors.toList());
    }

    public WordResponse getById(Long id) {
        Word word = wordRepository.findById(id).orElseThrow(WordNotFoundException::new);
        return ToDtoService.toDto(word);
    }

    public WordResponse addWord(WordRequest wordRequest) {
        Dictionary dictionary = dictionaryRepository.findById(wordRequest.getDictionaryId()).orElseThrow(DictionaryNotFoundException::new);

        checkByRule(dictionary.getRule(), wordRequest);
        if (wordRepository.existsWordByDictionaryIdAndWord(wordRequest.getDictionaryId(), wordRequest.getWord())){
            throw new WordAlreadyExistException();
        }

        Word word = new Word(wordRequest.getWord(), dictionary);
        return ToDtoService.toDto(wordRepository.save(word));
    }

    public WordResponse patchWord(WordRequest wordRequest) {
        Word word = wordRepository.findById(wordRequest.getId()).orElseThrow(WordNotFoundException::new);

        checkByRule(word.getDictionary().getRule(), wordRequest);
        if (wordRepository.existsWordByDictionaryIdAndWord(wordRequest.getDictionaryId(), wordRequest.getWord())){
            throw new WordAlreadyExistException();
        }

        word.setWord(wordRequest.getWord());
        return ToDtoService.toDto(wordRepository.save(word));
    }

    public void removeWord(Long id){
        wordRepository.deleteById(id);
    }

    private void checkByRule(Rule rule, WordRequest wordRequest){
        if (!wordRequest.getWord().matches(rule.getPattern())){
            throw new WordRuleException();
        }
    }


}
