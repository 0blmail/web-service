package com.where2beer.ws.common.helper;

import com.where2beer.ws.common.model.search.SearchCriterion;
import com.where2beer.ws.user.model.UserRole;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class SpecificationHelper {

    private SpecificationHelper() {
    }

    public static Predicate predicate(SearchCriterion criterion, Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Class<?> javaType = root.get(criterion.getColumn()).getJavaType();

        switch (criterion.getOperation()) {
            case LIKE:
                return builder.like(builder.lower(root.get(criterion.getColumn())), "%" + criterion.getValue().toLowerCase() + "%");
            case EQUAL:
                var value = castValue(criterion.getValue(), javaType);
                return builder.equal(root.get(criterion.getColumn()), value);
            case IN:
                var values = Arrays.stream(criterion.getValue().split(","))
                        .map(v -> castValue(v, javaType))
                        .collect(Collectors.toList());
                return root.get(criterion.getColumn()).in(values);
            default:
                return null;
        }
    }

    private static Object castValue(String valueStr, Class<?> javaType) {
        switch (javaType.getSimpleName()) {
            case "Boolean":
                return Boolean.parseBoolean(valueStr);
            case "UserRole":
                return UserRole.valueOf(valueStr);
            default:
                return null;
        }
    }
}
