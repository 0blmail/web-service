package com.where2beer.ws.country.service;

import com.where2beer.ws.country.dao.CountryDao;
import com.where2beer.ws.country.dao.CountrySpecification;
import com.where2beer.ws.country.dto.CountryDto;
import com.where2beer.ws.country.model.Country;
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

    public Page<Country> search(List<SearchCriterion> criteria, Pageable pageable) {
        var builder = new GenericSpecificationBuilder<Country>(criteria);

        return this.countryDao.findAll(builder.build(CountrySpecification::new), pageable);
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
