package com.example.mmtr3.entity;

import com.example.mmtr3.entity.extend.AbstractWord;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LatinWord extends AbstractWord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "word")
    private List<LatinTranslation> translations;

    public LatinWord(@NonNull String word) {
        super(word);
    }
}
