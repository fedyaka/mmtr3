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
@NoArgsConstructor
@AllArgsConstructor
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @NotNull
    private String pattern;

    @NotBlank
    @NotNull
    private String description;

    @OneToOne(mappedBy = "rule",fetch = FetchType.LAZY)
    private Dictionary dictionary;

    public Rule(String pattern, String description){
        this.pattern = pattern;
        this.description = description;
    }
}
