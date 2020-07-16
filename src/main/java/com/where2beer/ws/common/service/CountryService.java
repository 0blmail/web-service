package com.where2beer.ws.common.service;

import com.where2beer.ws.common.dao.CountryDao;
import com.where2beer.ws.common.dto.NewCountryDto;
import com.where2beer.ws.common.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryService {

    private final CountryDao countryDao;

    public List<Country> findAll() {
        return this.countryDao.findAll();
    }

    public Country create(NewCountryDto dto) {
        Country country = Country.builder()
                .label(dto.getLabel())
                .flag(dto.getFlag())
                .build();

        return this.countryDao.save(country);
    }

    public void delete(Long id) {
        this.countryDao.deleteById(id);
    }
}
