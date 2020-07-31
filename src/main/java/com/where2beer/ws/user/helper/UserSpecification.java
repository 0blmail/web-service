package com.where2beer.ws.user.helper;

import com.where2beer.ws.common.helper.SpecificationHelper;
import com.where2beer.ws.common.model.search.SearchCriterion;
import com.where2beer.ws.common.model.search.SearchOperation;
import com.where2beer.ws.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class UserSpecification implements Specification<User> {

    private final SearchCriterion criterion;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criterion.getOperation().equals(SearchOperation.LIKE) && criterion.getColumn().equals("text")) {
            String pattern = "%" + criterion.getValue().toLowerCase() + "%";
            Predicate emailLike = builder.like(builder.lower(root.get("email")), pattern);
            Predicate firstNameLike = builder.like(builder.lower(root.get("firstName")), pattern);
            Predicate lastNameLike = builder.like(builder.lower(root.get("lastName")), pattern);

            return builder.or(emailLike, firstNameLike, lastNameLike);
        }

        return SpecificationHelper.predicate(criterion, root, query, builder);
    }
}
