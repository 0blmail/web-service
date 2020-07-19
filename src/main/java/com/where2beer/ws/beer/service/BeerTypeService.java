package com.where2beer.ws.beer.service;

import com.where2beer.ws.beer.dao.BeerTypeDao;
import com.where2beer.ws.beer.dto.NewBeerTypeDto;
import com.where2beer.ws.beer.model.BeerType;
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

    public Page<BeerType> search(Pageable pageable) {
        return this.beerTypeDao.findAll(pageable);
    }

    public BeerType create(NewBeerTypeDto beerTypeDto) {
        var beerType = BeerType.builder()
                .label(beerTypeDto.getLabel())
                .build();

        return this.beerTypeDao.save(beerType);
    }

    public void delete(Long id) {
        this.beerTypeDao.deleteById(id);
    }
}
