package com.where2beer.ws.beer.model;

import com.where2beer.ws.country.model.Country;
import com.where2beer.ws.common.model.Auditable;
import com.where2beer.ws.common.model.Picture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Beer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double degree;

    @Column(nullable = false)
    private String background;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BeerColor color;

    @ManyToOne
    private BeerType type;

    @ManyToOne
    private Country country;

    @Column
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Picture picture;
}
