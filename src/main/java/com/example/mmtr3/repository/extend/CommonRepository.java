package com.example.mmtr3.repository.extend;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CommonRepository<E> extends CrudRepository<E, Long> {

    @Override
    List<E> findAll();

}
