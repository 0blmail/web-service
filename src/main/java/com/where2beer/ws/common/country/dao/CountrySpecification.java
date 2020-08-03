package com.where2beer.ws.common.country.dao;

import com.where2beer.ws.common.country.model.Country;
import com.where2beer.ws.common.model.search.SearchCriterion;
import com.where2beer.ws.common.model.search.SearchOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class CountrySpecification implements Specification<Country> {

    private final SearchCriterion criterion;

    @Override
    public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criterion.getOperation().equals(SearchOperation.LIKE) && criterion.getColumn().equals("search")) {
            var pattern = "%" + criterion.getValue().toLowerCase() + "+";

            return builder.like(builder.lower(root.get("label")), pattern);
        }

        return null;
    }
}
