package com.example.mmtr2.service;

import com.example.mmtr2.constant.ValidConstant;
import com.example.mmtr2.dto.LatinDTO;
import com.example.mmtr2.entity.WordLatin;
import com.example.mmtr2.exception.MyException.EmptyDictionaryListException;
import com.example.mmtr2.exception.MyException.WordAlreadyExistException;
import com.example.mmtr2.exception.MyException.WordNotFoundException;
import com.example.mmtr2.repository.WordLatinRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class LatinService{

    WordLatinRepository wordLatinRepository;

    public LatinService(WordLatinRepository wordLatinRepository){
        this.wordLatinRepository = wordLatinRepository;
    }

    public List<LatinDTO.Response.Public> findAll(){
        List<WordLatin> wordAll = wordLatinRepository.findAll();
        if (wordAll == null || wordAll.isEmpty()){
            throw new EmptyDictionaryListException();
        }
        return wordAll.stream().map(ToDtoService::toDto).collect(Collectors.toList());
    }

    public LatinDTO.Response.Public findById(@Min(value = 0, message = ValidConstant.MIN_ID)
                                                 Long id){
        Optional<WordLatin> word = wordLatinRepository.findById(id);
        if (word.isEmpty()){
            throw new WordNotFoundException();
        }
        return ToDtoService.toDto(word.get());
    }

    public LatinDTO.Response.Public findByWord(@Valid LatinDTO.Request.Create dto){
        Optional<WordLatin> byWord = wordLatinRepository.findByWord(dto.getWord());
        if (byWord.isEmpty()){
            throw new WordNotFoundException();
        }
        return ToDtoService.toDto(byWord.get());
    }

    public LatinDTO.Response.Public save(@Valid LatinDTO.Request.Create dto){
        if (wordLatinRepository.findByWord(dto.getWord()).isPresent()){
            throw new WordAlreadyExistException();
        }
        WordLatin wordLatin = new WordLatin(dto.getWord());
        wordLatinRepository.save(wordLatin);

        return ToDtoService.toDto(wordLatin);
    }

    public void deleteById(@Min(value = 0, message = ValidConstant.MIN_ID)
                           Long id){
        if (wordLatinRepository.findById(id).isEmpty()){
            throw new WordNotFoundException();
        }
        wordLatinRepository.deleteById(id);
    }

    @Transactional
    public void deleteByWord(@Valid LatinDTO.Request.Delete dto){
        if (wordLatinRepository.findByWord(dto.getWord()).isEmpty()){
            throw new WordNotFoundException();
        }
        wordLatinRepository.deleteByWord(dto.getWord());
    }

    @Transactional
    public LatinDTO.Response.Public patch(@Valid LatinDTO.Request.Patch patchDto) {
        Optional<WordLatin> wordOpt = wordLatinRepository.findById(patchDto.getId());
        if (wordOpt.isEmpty()){
            throw new WordNotFoundException();
        }
        WordLatin wordLatin = wordOpt.get();
        wordLatin.setWord(patchDto.getWord());
        return ToDtoService.toDto(wordLatinRepository.save(wordLatin));
    }
}
