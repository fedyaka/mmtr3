package com.example.mmtr2.entity;

import com.example.mmtr2.entity.extend.AbstractTranslation;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LatinTranslation extends AbstractTranslation {

    @ManyToOne(fetch = FetchType.LAZY)
    private LatinWord word;

    public LatinTranslation(@NonNull String translation) {
        super(translation);
    }
}
