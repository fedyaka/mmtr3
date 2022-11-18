package com.example.mmtr2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class NumberTranslation {
    @Id
    @GeneratedValue()
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String translation;

    @ManyToOne(fetch = FetchType.LAZY)
    private WordNumber wordNumber;
}
