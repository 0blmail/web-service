package com.where2beer.ws.user.controller;

import com.where2beer.ws.common.exception.BadRequestException;
import com.where2beer.ws.common.helper.CriteriaHelper;
import com.where2beer.ws.common.model.dto.CreateGroup;
import com.where2beer.ws.common.model.dto.UpdateGroup;
import com.where2beer.ws.common.model.search.SearchCriterion;
import com.where2beer.ws.user.dto.UserDto;
import com.where2beer.ws.user.model.User;
import com.where2beer.ws.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User create(@RequestBody @Validated(CreateGroup.class) UserDto dto) {
        return this.userService.create(dto);
    }

    @GetMapping("search")
    public Page<User> search(@RequestParam("criteria") String params, Pageable pageable) {
        List<SearchCriterion> criteria = CriteriaHelper.fromString(params);

        return this.userService.search(criteria, pageable);
    }

    @GetMapping("search/email/{email}")
    public boolean searchByEmail(@PathVariable String email) {
        return this.userService.emailExist(email);
    }

    @GetMapping("search/pseudo/{pseudo}")
    public boolean searchByPseudo(@PathVariable String pseudo) {
        return this.userService.pseudoExist(pseudo);
    }

    @PutMapping("{id}")
    public User update(@RequestBody @Validated(UpdateGroup.class) UserDto dto, @PathVariable Long id) {
        if (!id.equals(dto.getId())) {
            throw new BadRequestException();
        }

        return this.userService.update(dto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.userService.delete(id);
    }
}
