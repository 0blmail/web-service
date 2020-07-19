package com.where2beer.ws.beer.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class NewBeerTypeDto {

    @NotEmpty
    private String label;
}
