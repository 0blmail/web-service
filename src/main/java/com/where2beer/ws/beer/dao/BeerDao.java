package com.where2beer.ws.beer.dao;

import com.where2beer.ws.beer.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerDao extends JpaRepository<Beer, Long>, JpaSpecificationExecutor<Beer> {
}
