package com.example.mmtr2.entity;

import com.example.mmtr2.entity.extend.AbstractTranslation;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NumberTranslation extends AbstractTranslation {

    @ManyToOne(fetch = FetchType.LAZY)
    private NumberWord numberWord;

    public NumberTranslation(@NonNull String translation) {
        super(translation);
    }
}
