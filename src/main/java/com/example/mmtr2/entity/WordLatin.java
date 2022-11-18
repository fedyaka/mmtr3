package com.example.mmtr2.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class WordLatin {
    @Id
    @GeneratedValue()
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String word;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LatinTranslation> translations;
}
