package com.where2beer.ws.common.country.dao;

import com.where2beer.ws.common.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDao extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {
}
