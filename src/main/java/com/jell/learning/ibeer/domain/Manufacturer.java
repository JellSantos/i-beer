package com.jell.learning.ibeer.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manufacturer {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(generator = "manufacturer_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "manufacturer_seq", sequenceName = "manufacturer_id_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birthplace;

    @OneToMany
    private List<Beer> beerList;
}
