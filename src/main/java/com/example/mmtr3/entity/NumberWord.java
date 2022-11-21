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
public class NumberWord extends AbstractWord {


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "word")
    private List<NumberTranslation> translations;

    public NumberWord(@NonNull String word) {
        super(word);
    }
}
