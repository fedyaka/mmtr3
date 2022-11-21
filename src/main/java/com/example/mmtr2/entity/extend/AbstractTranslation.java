package com.example.mmtr2.entity.extend;

import lombok.*;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractTranslation extends AbstractEntity{

    @NonNull
    private String translation;

}
