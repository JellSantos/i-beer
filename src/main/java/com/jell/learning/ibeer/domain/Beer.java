package com.jell.learning.ibeer.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table
@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Beer {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(generator = "beer_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "beer_seq", sequenceName = "bee_id_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer ibu;

    @Column(nullable = false)
    private String style;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer milliliter;

    //TODO: enable when create manufacturer table
    //private String manufacturer;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
