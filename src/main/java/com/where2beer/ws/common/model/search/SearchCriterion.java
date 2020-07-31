package com.where2beer.ws.common.model.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriterion {

    private String column;

    private SearchOperation operation;

    private String value;
}
