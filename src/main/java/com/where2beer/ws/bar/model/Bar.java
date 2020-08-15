package com.where2beer.ws.bar.model;

import com.where2beer.ws.beer.model.Beer;
import com.where2beer.ws.common.model.Address;
import com.where2beer.ws.common.model.Auditable;
import com.where2beer.ws.common.model.Picture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bar extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    private List<Beer> beers;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(columnDefinition = "TEXT")
    private String description;
}
