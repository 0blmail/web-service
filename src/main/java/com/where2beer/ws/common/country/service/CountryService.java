package com.where2beer.ws.common.country.service;

import com.where2beer.ws.common.country.dao.CountryDao;
import com.where2beer.ws.common.country.dto.NewCountryDto;
import com.where2beer.ws.common.country.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Country> search(Pageable pageable) {
        return this.countryDao.findAll(pageable);
    }

    public void delete(Long id) {
        this.countryDao.deleteById(id);
    }
}
