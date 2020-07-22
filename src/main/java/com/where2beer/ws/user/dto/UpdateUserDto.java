package com.where2beer.ws.user.dto;

import com.where2beer.ws.user.model.UserRole;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserDto {

    @NotNull
    private Long id;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String pseudo;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private boolean emailVerified;

    @NotNull
    private boolean disabled;

    @NotNull
    private UserRole role;
}
