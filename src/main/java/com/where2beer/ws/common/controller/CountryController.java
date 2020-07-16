package com.where2beer.ws.common.controller;

import com.where2beer.ws.common.dto.NewCountryDto;
import com.where2beer.ws.common.model.Country;
import com.where2beer.ws.common.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public List<Country> findAll() {
        return this.countryService.findAll();
    }

    @PostMapping
    public Country create(@RequestBody @Valid NewCountryDto countryDto) {
        return this.countryService.create(countryDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.countryService.delete(id);
    }
}
