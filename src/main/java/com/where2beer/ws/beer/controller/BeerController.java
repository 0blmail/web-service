package com.where2beer.ws.beer.controller;

import com.where2beer.ws.beer.dto.NewBeerDto;
import com.where2beer.ws.beer.dto.UpdateBeerDto;
import com.where2beer.ws.beer.model.Beer;
import com.where2beer.ws.beer.service.BeerService;
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
@RequestMapping("beers")
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @PostMapping("search")
    public Page<Beer> search(Pageable pageable) {
        return this.beerService.search(pageable);
    }

    @PostMapping
    public Beer create(@RequestBody @Valid NewBeerDto beerDto) {
        return this.beerService.create(beerDto);
    }

    @PutMapping("{id}")
    public Beer update(@RequestBody @Valid UpdateBeerDto beerDto) {
        return this.beerService.update(beerDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.beerService.delete(id);
    }
}
