package com.where2beer.ws.common.country.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class NewCountryDto {

    @NotEmpty
    private String label;

    @NotEmpty
    private String flag;
}
