package com.where2beer.ws.bar.dto;

import com.where2beer.ws.bar.model.Schedule;
import com.where2beer.ws.beer.model.Beer;
import com.where2beer.ws.common.model.Address;
import com.where2beer.ws.common.model.Picture;
import com.where2beer.ws.common.model.dto.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class BarDto {

    @NotNull(groups = UpdateGroup.class)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private List<Beer> beers;

    @NotEmpty
    private List<Picture> pictures;

    @NotEmpty
    @Size(min = 7, max = 7)
    private List<Schedule> schedules;

    @NotNull
    private Address address;
}
