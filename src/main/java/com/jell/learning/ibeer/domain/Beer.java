package com.jell.learning.ibeer.domain;

import lombok.*;

import javax.persistence.*;

@Table
@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Beer {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(generator = "beer_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "beer_seq", sequenceName = "bee_id_seq")
    private Long id;

    @Column(nullable = false)
    private String name;
}
