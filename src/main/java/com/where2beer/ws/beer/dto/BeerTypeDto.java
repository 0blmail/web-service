package com.where2beer.ws.beer.dto;

import com.where2beer.ws.common.model.dto.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class BeerTypeDto {

    @NotNull(groups = UpdateGroup.class)
    private Long id;

    @NotEmpty
    private String label;
}
