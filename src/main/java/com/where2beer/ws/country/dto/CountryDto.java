package com.where2beer.ws.country.dto;

import com.where2beer.ws.common.model.dto.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CountryDto {

    @NotNull(groups = UpdateGroup.class)
    private Long id;

    @NotEmpty
    private String label;

    @NotEmpty
    private String flag;
}
