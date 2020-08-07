package com.where2beer.ws.beer.controller;

import com.where2beer.ws.beer.dto.BeerDto;
import com.where2beer.ws.beer.model.Beer;
import com.where2beer.ws.beer.service.BeerService;
import com.where2beer.ws.common.exception.BadRequestException;
import com.where2beer.ws.common.helper.CriteriaHelper;
import com.where2beer.ws.common.model.dto.UpdateGroup;
import com.where2beer.ws.common.model.search.SearchCriterion;
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
@RequestMapping("beers")
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping("search")
    public Page<Beer> search(@RequestParam(value = "criteria", required = false) String params, Pageable pageable) {
        List<SearchCriterion> criteria = CriteriaHelper.fromString(params);

        return this.beerService.search(criteria, pageable);
    }

    @PostMapping
    public Beer create(@RequestBody @Validated BeerDto beerDto) {
        return this.beerService.create(beerDto);
    }

    @GetMapping("{id}")
    public Beer find(@PathVariable Long id) {
        return this.beerService.find(id);
    }

    @PutMapping("{id}")
    public Beer update(@PathVariable Long id, @RequestBody @Validated(UpdateGroup.class) BeerDto beerDto) {
        if (!id.equals(beerDto.getId())) {
            throw new BadRequestException();
        }

        return this.beerService.update(beerDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.beerService.delete(id);
    }
}
