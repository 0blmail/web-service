package com.where2beer.ws.common.helper;

import com.where2beer.ws.common.exception.BadRequestException;
import com.where2beer.ws.common.model.search.SearchCriterion;
import com.where2beer.ws.common.model.search.SearchOperation;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class CriteriaHelper {

    private CriteriaHelper() {
    }

    public static List<SearchCriterion> fromString(String criteriaParams) {
        try {
            String codes = Arrays.stream(SearchOperation.values())
                    .map(SearchOperation::toString)
                    .map(character -> "\\" + character)
                    .collect(Collectors.joining("|"));
            var pattern = Pattern.compile("(\\w+?)(" + codes + ")(\\w+?);");
            var criteria = new ArrayList<SearchCriterion>();

            if (!StringUtils.isBlank(criteriaParams)) {
                var matcher = pattern.matcher(criteriaParams + ";");
                while (matcher.find()) {
                    var operation = SearchOperation.fromString(matcher.group(2));
                    criteria.add(new SearchCriterion(matcher.group(1), operation, matcher.group(3)));
                }
            }

            return criteria;
        } catch (IllegalArgumentException e) {
            throw new BadRequestException();
        }
    }
}
