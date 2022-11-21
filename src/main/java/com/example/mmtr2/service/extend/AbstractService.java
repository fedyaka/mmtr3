package com.example.mmtr2.service.extend;

import com.example.mmtr2.entity.extend.AbstractEntity;
import com.example.mmtr2.repository.extend.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>>
        implements CommonService<E>{

    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }
}
