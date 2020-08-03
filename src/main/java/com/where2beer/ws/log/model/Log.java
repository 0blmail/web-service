package com.where2beer.ws.log.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.envers.RevisionType;

@Data
@Builder
public class Log<T> {
    private T entity;
    private long timestamp;
    private RevisionType type;
}
