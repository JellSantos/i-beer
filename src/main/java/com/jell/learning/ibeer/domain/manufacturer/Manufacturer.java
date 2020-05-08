package com.jell.learning.ibeer.domain.manufacturer;

import com.jell.learning.ibeer.audit.DateAudit;
import com.jell.learning.ibeer.domain.beer.Beer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Table
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manufacturer {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(generator = "manufacturer_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "manufacturer_seq", sequenceName = "manufacturer_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birthplace;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Beer> beers;

    @Embedded
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DateAudit dateAudit = new DateAudit();
}
