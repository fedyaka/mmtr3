package com.example.mmtr3.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "word_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @NotBlank
    private String word;

    @OneToMany(mappedBy = "word",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Translation> translations;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Dictionary dictionary;

    public Word(String word, Dictionary dictionary){
        this.word = word;
        this.dictionary = dictionary;
    }
}
