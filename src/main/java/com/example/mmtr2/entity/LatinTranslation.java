package com.example.mmtr2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class LatinTranslation {
    @Id
    @GeneratedValue()
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String translation;

    @ManyToOne(fetch = FetchType.LAZY)
    private WordLatin wordLatin;

}
