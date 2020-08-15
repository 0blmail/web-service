package com.where2beer.ws.bar.dao;

import com.where2beer.ws.bar.model.Bar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BarDao extends JpaRepository<Bar, Long>, JpaSpecificationExecutor<Bar> {
}
