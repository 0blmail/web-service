package com.where2beer.ws.beer.service;

import com.where2beer.ws.beer.dao.BeerTypeDao;
import com.where2beer.ws.beer.dto.BeerTypeDto;
import com.where2beer.ws.beer.helper.BeerTypeSpecification;
import com.where2beer.ws.beer.model.BeerType;
import com.where2beer.ws.common.exception.NotFoundException;
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
public class BeerTypeService {

    private final BeerTypeDao beerTypeDao;

    public List<BeerType> findAll() {
        return this.beerTypeDao.findAll();
    }

    public Page<BeerType> search(List<SearchCriterion> criteria, Pageable pageable) {
        var builder = new GenericSpecificationBuilder<BeerType>(criteria);

        return this.beerTypeDao.findAll(builder.build(BeerTypeSpecification::new), pageable);
    }

    public BeerType create(BeerTypeDto beerTypeDto) {
        var beerType = BeerType.builder()
                .label(beerTypeDto.getLabel())
                .build();

        return this.beerTypeDao.save(beerType);
    }

    public BeerType update(BeerTypeDto beerTypeDto) {
        var beerType = this.beerTypeDao.findById(beerTypeDto.getId()).orElseThrow(NotFoundException::new);
        beerType.setLabel(beerTypeDto.getLabel());

        return this.beerTypeDao.save(beerType);
    }

    public void delete(Long id) {
        this.beerTypeDao.deleteById(id);
    }
}
