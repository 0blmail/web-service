package com.where2beer.ws.beer.service;

import com.where2beer.ws.beer.dao.BeerDao;
import com.where2beer.ws.beer.dto.BeerDto;
import com.where2beer.ws.beer.helper.BeerSpecification;
import com.where2beer.ws.beer.model.Beer;
import com.where2beer.ws.common.exception.custom.NotFoundException;
import com.where2beer.ws.common.helper.GenericSpecificationBuilder;
import com.where2beer.ws.common.model.search.SearchCriterion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BeerService {

    private final BeerDao beerDao;

    public Page<Beer> search(List<SearchCriterion> criteria, Pageable pageable) {
        var builder = new GenericSpecificationBuilder<Beer>(criteria);

        return this.beerDao.findAll(builder.build(BeerSpecification::new), pageable);
    }

    public Beer create(BeerDto beerDto) {
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

    public Beer find(Long id) {
        return this.beerDao.findById(id).orElse(null);
    }

    public Beer update(BeerDto beerDto) {
        var beer = this.beerDao.findById(beerDto.getId()).orElseThrow(NotFoundException::new);

        beer.setName(beerDto.getName());
        beer.setDegree(beerDto.getDegree());
        beer.setColor(beerDto.getColor());
        beer.setType(beerDto.getType());
        beer.setCountry(beerDto.getCountry());
        beer.setBackground(beerDto.getBackground());
        beer.setSummary(beerDto.getSummary());
        beer.setDescription(beerDto.getDescription());

        return this.beerDao.save(beer);
    }

    public void delete(Long id) {
        this.beerDao.deleteById(id);
    }
}
