package com.where2beer.ws.beer.dto;

import com.where2beer.ws.beer.model.BeerColor;
import com.where2beer.ws.beer.model.BeerType;
import com.where2beer.ws.country.model.Country;
import com.where2beer.ws.common.model.Picture;
import com.where2beer.ws.common.model.dto.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BeerDto {

    @NotNull(groups = UpdateGroup.class)
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    @Min(value = 0)
    @Max(value = 100)
    private Double degree;

    @NotEmpty
    @Size(min = 7, max = 7)
    private String background;

    @NotNull
    private BeerColor color;

    @NotNull
    private BeerType type;

    @NotNull
    private Country country;

    private String summary;

    private String description;

    @NotNull
    private Picture picture;
}
