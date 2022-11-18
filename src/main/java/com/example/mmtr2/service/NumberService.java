package com.example.mmtr2.service;

import com.example.mmtr2.constant.ValidConstant;
import com.example.mmtr2.dto.NumberDTO;
import com.example.mmtr2.entity.WordNumber;
import com.example.mmtr2.exception.MyException.EmptyDictionaryListException;
import com.example.mmtr2.exception.MyException.WordAlreadyExistException;
import com.example.mmtr2.exception.MyException.WordNotFoundException;
import com.example.mmtr2.repository.WordNumberRepository;
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
public class NumberService {

    WordNumberRepository wordNumberRepository;

    public NumberService(WordNumberRepository wordNumberRepository) {
        this.wordNumberRepository = wordNumberRepository;
    }

    public List<NumberDTO.Response.Public> findAll(){
        List<WordNumber> wordAll = wordNumberRepository.findAll();
        if (wordAll == null || wordAll.isEmpty()){
            throw new EmptyDictionaryListException();
        }
        return wordAll.stream().map(ToDtoService::toDto).collect(Collectors.toList());
    }

    public NumberDTO.Response.Public findById(@Min(value = 0, message = ValidConstant.MIN_ID)
                                                 Long id){
        Optional<WordNumber> word = wordNumberRepository.findById(id);
        if (word.isEmpty()){
            throw new WordNotFoundException();
        }
        return ToDtoService.toDto(word.get());
    }

    public NumberDTO.Response.Public findByWord(@Valid NumberDTO.Request.Create dto){
        Optional<WordNumber> byWord = wordNumberRepository.findByWord(dto.getWord());
        if (byWord.isEmpty()){
            throw new WordNotFoundException();
        }
        return ToDtoService.toDto(byWord.get());
    }

    public NumberDTO.Response.Public save(@Valid NumberDTO.Request.Create dto){
        if (wordNumberRepository.findByWord(dto.getWord()).isPresent()){
            throw new WordAlreadyExistException();
        }
        WordNumber wordLatin = new WordNumber(dto.getWord());
        wordNumberRepository.save(wordLatin);

        return ToDtoService.toDto(wordLatin);
    }

    public void deleteById(@Min(value = 0, message = ValidConstant.MIN_ID)
                           Long id){
        if (wordNumberRepository.findById(id).isEmpty()){
            throw new WordNotFoundException();
        }
        wordNumberRepository.deleteById(id);
    }

    @Transactional
    public void deleteByWord(@Valid NumberDTO.Request.Delete dto){
        if (wordNumberRepository.findByWord(dto.getWord()).isEmpty()){
            throw new WordNotFoundException();
        }
        wordNumberRepository.deleteByWord(dto.getWord());
    }

    @Transactional
    public NumberDTO.Response.Public patch(@Valid NumberDTO.Request.Patch patchDto) {
        Optional<WordNumber> wordOpt = wordNumberRepository.findById(patchDto.getId());
        if (wordOpt.isEmpty()){
            throw new WordNotFoundException();
        }
        WordNumber wordLatin = wordOpt.get();
        wordLatin.setWord(patchDto.getWord());
        return ToDtoService.toDto(wordNumberRepository.save(wordLatin));
    }
}
