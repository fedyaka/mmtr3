package com.example.mmtr3.entity;

import com.example.mmtr3.entity.extend.AbstractTranslation;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NumberTranslation extends AbstractTranslation {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private NumberWord word;

    public NumberTranslation(@NonNull String translation) {
        super(translation);
    }

    public NumberTranslation(@NonNull String translation, NumberWord word) {
        super(translation);
        this.word = word;
    }
}
