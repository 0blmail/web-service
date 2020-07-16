package com.where2beer.ws.common.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
public class Country extends Auditable {

    @Id
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private String flag;
}
