package com.where2beer.ws.common.helper;

import com.where2beer.ws.common.model.search.SearchCriterion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GenericSpecificationBuilder<T> {

    private final List<SearchCriterion> criteria;

    public Specification<T> build(Function<SearchCriterion, Specification<T>> converter) {
        if (criteria.isEmpty()) {
            return null;
        }

        List<Specification<T>> specifications = criteria.stream()
                .map(converter)
                .collect(Collectors.toList());

        Specification<T> result = specifications.get(0);
        for (int i = 1; i < criteria.size(); i++) {
            result = Specification.where(result).and(specifications.get(i));
        }

        return result;
    }
}
