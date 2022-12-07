package com.example.mmtr3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "translation_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @NotBlank
    private String translation;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Word word;

}
