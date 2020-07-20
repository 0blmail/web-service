package com.where2beer.ws.beer.service;

import com.where2beer.ws.beer.dao.BeerDao;
import com.where2beer.ws.beer.dto.NewBeerDto;
import com.where2beer.ws.beer.dto.UpdateBeerDto;
import com.where2beer.ws.beer.model.Beer;
import com.where2beer.ws.common.exception.TechnicalException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BeerService {

    private final BeerDao beerDao;

    public Page<Beer> search(Pageable pageable) {
        return this.beerDao.findAll(pageable);
    }

    public Beer create(NewBeerDto beerDto) {
        var beer = Beer.builder()
                .name(beerDto.getName())
                .degree(beerDto.getDegree())
                .background(beerDto.getBackground())
                .color(beerDto.getColor())
                .type(beerDto.getType())
                .country(beerDto.getCountry())
                .summary(beerDto.getSummary())
                .description(beerDto.getDescription())
                .picture(beerDto.getPicture())
                .build();

        return this.beerDao.save(beer);
    }

    public Beer update(UpdateBeerDto beerDto) {
        var beer = this.beerDao.findById(beerDto.getId()).orElseThrow(TechnicalException::new);

        return this.beerDao.save(beer);
    }

    public void delete(Long id) {
        this.beerDao.deleteById(id);
    }
}
