package com.where2beer.ws.user.controller;

import com.where2beer.ws.user.dto.NewUserDto;
import com.where2beer.ws.user.dto.UpdateUserDto;
import com.where2beer.ws.user.model.User;
import com.where2beer.ws.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User create(@RequestBody @Valid NewUserDto dto) {
        return this.userService.create(dto);
    }

    @PostMapping("search")
    public Page<User> search(Pageable pageable) {
        return this.userService.search(pageable);
    }

    @PutMapping("{id}")
    public User update(@RequestBody @Valid UpdateUserDto dto, @PathVariable Long id) {
        return this.userService.update(dto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.userService.delete(id);
    }
}
