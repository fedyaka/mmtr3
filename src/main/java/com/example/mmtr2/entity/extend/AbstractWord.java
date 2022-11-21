package com.example.mmtr2.entity.extend;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractWord extends AbstractEntity{

    @NonNull
    @Column(name = "word")
    private String word;

}
