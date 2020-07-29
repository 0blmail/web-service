package com.where2beer.ws.user.dto;

import com.where2beer.ws.common.model.CreateGroup;
import com.where2beer.ws.common.model.UpdateGroup;
import com.where2beer.ws.user.model.UserRole;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @NotNull(groups = UpdateGroup.class)
    private Long id;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty(groups = CreateGroup.class)
    @Size(min = 8, max = 255, groups = CreateGroup.class)
    private String password;

    @NotEmpty
    private String pseudo;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private UserRole role;

    @NotNull(groups = UpdateGroup.class)
    private boolean disabled;

    @NotNull
    private boolean emailVerified;
}
