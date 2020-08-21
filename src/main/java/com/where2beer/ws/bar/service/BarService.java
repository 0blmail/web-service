package com.where2beer.ws.bar.service;

import com.where2beer.ws.bar.dao.BarDao;
import com.where2beer.ws.bar.dto.BarDto;
import com.where2beer.ws.bar.helper.BarSpecification;
import com.where2beer.ws.bar.model.Bar;
import com.where2beer.ws.common.exception.NotFoundException;
import com.where2beer.ws.common.helper.GenericSpecificationBuilder;
import com.where2beer.ws.common.model.Address;
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
public class BarService {

    private final BarDao barDao;

    public Page<Bar> search(List<SearchCriterion> criteria, Pageable pageable) {
        var builder = new GenericSpecificationBuilder<Bar>(criteria);

        return this.barDao.findAll(builder.build(BarSpecification::new), pageable);
    }

    public Bar create(BarDto barDto) {
        var addressDto = barDto.getAddress();

        var address = Address.builder()
                .line(addressDto.getLine())
                .zipCode(addressDto.getZipCode())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .longitude(addressDto.getLongitude())
                .longitudeInRad(Math.toRadians(addressDto.getLongitude()))
                .latitude(addressDto.getLatitude())
                .latitudeInRad(Math.toRadians(addressDto.getLatitude()))
                .build();

        var bar = Bar.builder()
                .name(barDto.getName())
                .beers(barDto.getBeers())
                .pictures(barDto.getPictures())
                .address(address)
                .build();

        return this.barDao.save(bar);
    }

    public Bar update(BarDto barDto) {
        var bar = this.barDao.findById(barDto.getId()).orElseThrow(NotFoundException::new);

        bar.setName(barDto.getName());
        bar.setAddress(barDto.getAddress());
        bar.setSchedules(barDto.getSchedules());
        bar.setBeers(barDto.getBeers());
        bar.setPictures(barDto.getPictures());

        return this.barDao.save(bar);
    }

    public void delete(Long id) {
        this.barDao.deleteById(id);
    }
}
