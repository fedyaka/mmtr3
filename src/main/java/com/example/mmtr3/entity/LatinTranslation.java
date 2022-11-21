package com.example.mmtr3.entity;

import com.example.mmtr3.entity.extend.AbstractTranslation;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LatinTranslation extends AbstractTranslation {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private LatinWord word;

    public LatinTranslation(@NonNull String translation) {
        super(translation);
    }

    public LatinTranslation(@NonNull String translation, LatinWord word) {
        super(translation);
        this.word = word;
    }
}
