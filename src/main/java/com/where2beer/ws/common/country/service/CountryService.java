package com.where2beer.ws.common.country.service;

import com.where2beer.ws.common.country.dao.CountryDao;
import com.where2beer.ws.common.country.dto.CountryDto;
import com.where2beer.ws.common.country.model.Country;
import com.where2beer.ws.common.exception.NotFoundException;
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

    public Country create(CountryDto dto) {
        var country = Country.builder()
                .label(dto.getLabel())
                .flag(dto.getFlag())
                .build();

        return this.countryDao.save(country);
    }

    public Page<Country> search(Pageable pageable) {
        return this.countryDao.findAll(pageable);
    }

    public Country update(CountryDto dto) {
        var country = this.countryDao.findById(dto.getId()).orElseThrow(NotFoundException::new);
        country.setLabel(dto.getLabel());
        country.setFlag(dto.getFlag());

        return this.countryDao.save(country);
    }

    public void delete(Long id) {
        this.countryDao.deleteById(id);
    }
}
