package com.example.mmtr2.entity;

import com.example.mmtr2.entity.extend.AbstractWord;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LatinWord extends AbstractWord {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LatinTranslation> translations;

    public LatinWord(@NonNull String word) {
        super(word);
    }
}
