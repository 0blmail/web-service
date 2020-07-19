package com.where2beer.ws.beer.controller;

import com.where2beer.ws.beer.dto.NewBeerTypeDto;
import com.where2beer.ws.beer.model.BeerType;
import com.where2beer.ws.beer.service.BeerTypeService;
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
@RequestMapping("beers/types")
@RequiredArgsConstructor
public class BeerTypeController {

    private final BeerTypeService beerTypeService;

    @GetMapping
    public List<BeerType> findAll() {
        return this.beerTypeService.findAll();
    }

    @PostMapping("search")
    public Page<BeerType> search(Pageable pageable) {
        return this.beerTypeService.search(pageable);
    }

    @PostMapping
    public BeerType create(@RequestBody @Valid NewBeerTypeDto beerTypeDto) {
        return this.beerTypeService.create(beerTypeDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.beerTypeService.delete(id);
    }
}
