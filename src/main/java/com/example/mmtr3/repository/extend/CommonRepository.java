package com.example.mmtr3.repository.extend;

import com.example.mmtr3.entity.extend.AbstractEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface CommonRepository<E extends AbstractEntity> extends CrudRepository<E, Long> {

    @Override
    List<E> findAll();

    @Override
    Optional<E> findById(Long aLong);

    @Override
    <S extends E> S save(S entity);

    @Override
    void delete(E entity);

    @Override
    void deleteById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();


}
