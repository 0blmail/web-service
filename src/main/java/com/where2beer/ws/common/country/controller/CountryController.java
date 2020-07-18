package com.where2beer.ws.common.country.controller;

import com.where2beer.ws.common.country.dto.NewCountryDto;
import com.where2beer.ws.common.country.model.Country;
import com.where2beer.ws.common.country.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PostMapping("search")
    public Page<Country> search(Pageable pageable) {
        return this.countryService.search(pageable);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.countryService.delete(id);
    }
}
