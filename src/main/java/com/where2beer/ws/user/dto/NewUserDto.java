package com.where2beer.ws.user.dto;

import com.where2beer.ws.user.model.UserRole;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewUserDto {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, max = 255)
    private String password;

    @NotEmpty
    private String pseudo;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private UserRole role;

    @NotNull
    private boolean emailVerified;
}
