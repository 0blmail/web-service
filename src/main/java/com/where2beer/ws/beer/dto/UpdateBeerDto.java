package com.where2beer.ws.beer.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateBeerDto {

    @NotNull
    private Long id;
}
