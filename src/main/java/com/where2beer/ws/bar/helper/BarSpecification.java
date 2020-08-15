package com.where2beer.ws.bar.helper;

import com.where2beer.ws.bar.model.Bar;
import com.where2beer.ws.common.helper.SpecificationHelper;
import com.where2beer.ws.common.model.search.SearchCriterion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class BarSpecification implements Specification<Bar> {

    private final SearchCriterion criterion;

    @Override
    public Predicate toPredicate(Root<Bar> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return SpecificationHelper.predicate(criterion, root, query, builder);
    }
}
