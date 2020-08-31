package com.where2beer.ws.country.controller;

import com.where2beer.ws.common.exception.RestExceptionEnum;
import com.where2beer.ws.common.exception.custom.BadRequestException;
import com.where2beer.ws.common.helper.CriteriaHelper;
import com.where2beer.ws.common.model.dto.UpdateGroup;
import com.where2beer.ws.common.model.search.SearchCriterion;
import com.where2beer.ws.country.dto.CountryDto;
import com.where2beer.ws.country.model.Country;
import com.where2beer.ws.country.service.CountryService;
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
@RequestMapping("countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public List<Country> findAll() {
        return this.countryService.findAll();
    }

    @PostMapping
    public Country create(@RequestBody @Validated CountryDto countryDto) {
        return this.countryService.create(countryDto);
    }

    @GetMapping("search")
    public Page<Country> search(@RequestParam(value = "criteria", required = false) String params, Pageable pageable) {
        List<SearchCriterion> criteria = CriteriaHelper.fromString(params);

        return this.countryService.search(criteria, pageable);
    }

    @PutMapping("{id}")
    public Country update(@PathVariable Long id, @RequestBody @Validated(UpdateGroup.class) CountryDto countryDto) {
        if (!id.equals(countryDto.getId())) {
            throw new BadRequestException(RestExceptionEnum.DIFFERENT_ID);
        }

        return this.countryService.update(countryDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.countryService.delete(id);
    }
}
