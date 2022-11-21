package com.example.mmtr3.service.extend;

import com.example.mmtr3.constant.ValidConstant;
import com.example.mmtr3.dto.request.WordRequest;
import com.example.mmtr3.dto.request.patch.PatchWordRequest;
import com.example.mmtr3.dto.response.WordResponse;
import com.example.mmtr3.entity.LatinWord;
import com.example.mmtr3.entity.extend.AbstractWord;
import com.example.mmtr3.exception.MyException.EmptyDictionaryListException;
import com.example.mmtr3.exception.MyException.WordAlreadyExistException;
import com.example.mmtr3.exception.MyException.WordNotFoundException;
import com.example.mmtr3.repository.extend.WordRepository;
import com.example.mmtr3.service.ToDtoService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractWordService<E extends AbstractWord, R extends WordRepository<E>>
        extends AbstractService<E, R>
        implements WordService<E>{

    public AbstractWordService(R repository) {
        super(repository);
    }

    public List<WordResponse> findAll(){
        List<E> wordAll = repository.findAll();
        if (wordAll == null || wordAll.isEmpty()){
            throw new EmptyDictionaryListException();
        }
        return wordAll.stream().map(ToDtoService::toDto).collect(Collectors.toList());
    }

    public WordResponse findById(@Min(value = 0, message = ValidConstant.MIN_ID)
                                 Long id){
        Optional<E> word = repository.findById(id);
        if (word.isEmpty()){
            throw new WordNotFoundException();
        }
        return ToDtoService.toDto(word.get());
    }

    public WordResponse findByWord(@Valid WordRequest dto){
        Optional<LatinWord> byWord = repository.findByWord(dto.getWord());
        if (byWord.isEmpty()){
            throw new WordNotFoundException();
        }
        return ToDtoService.toDto(byWord.get());
    }

    public WordResponse save(@Valid WordRequest dto){
        if (repository.findByWord(dto.getWord()).isPresent()){
            throw new WordAlreadyExistException();
        }
        E word = newE();
        word.setWord(dto.getWord());
        repository.save(word);

        return ToDtoService.toDto(word);
    }

    public WordResponse patch(@Valid PatchWordRequest patchDto) {
        Optional<E> wordOpt = repository.findById(patchDto.getId());
        if (wordOpt.isEmpty()){
            throw new WordNotFoundException();
        }
        E latinWord = wordOpt.get();
        latinWord.setWord(patchDto.getWord());
        return ToDtoService.toDto(repository.save(latinWord));
    }

    @Transactional
    public void deleteByWord(@Valid WordRequest dto){
        if (repository.findByWord(dto.getWord()).isEmpty()){
            throw new WordNotFoundException();
        }
        repository.deleteByWord(dto.getWord());
    }

    public void deleteById(@Min(value = 0, message = ValidConstant.MIN_ID)
                           Long id) {
        if (repository.findById(id).isEmpty()){
            throw new WordNotFoundException();
        }
        repository.deleteById(id);
    }

    abstract protected E newE();
}
