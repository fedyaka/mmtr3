package com.example.mmtr2.entity.extend;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

}
