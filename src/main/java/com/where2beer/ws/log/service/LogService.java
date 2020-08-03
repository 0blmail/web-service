package com.where2beer.ws.log.service;

import com.where2beer.ws.log.model.Log;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LogService {

    private final EntityManager entityManager;

    public <T> List<Log<T>> findAll(Class<T> type, long timestamp) {
        AuditQuery query = AuditReaderFactory.get(this.entityManager)
                .createQuery()
                .forRevisionsOfEntity(type, false, true);

        List<Object[]> results = query.getResultList();
        List<Log<T>> audits = new ArrayList<>();
        for (Object[] result : results) {
            var builder = Log.<T>builder();
            builder.entity((T) result[0]);
            builder.timestamp(((DefaultRevisionEntity) result[1]).getTimestamp());
            builder.type((RevisionType) result[2]);

            audits.add(builder.build());
        }

        return audits;
    }
}
