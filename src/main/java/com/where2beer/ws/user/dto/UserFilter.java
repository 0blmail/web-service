package com.where2beer.ws.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserFilter {

    @NotNull
    private String search = "";
}
