package com.where2beer.ws.common.dao;

import com.where2beer.ws.common.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDao extends JpaRepository<Country, Long> {
}
