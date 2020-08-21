package com.where2beer.ws.bar.helper;

import com.where2beer.ws.bar.model.Bar;
import com.where2beer.ws.bar.model.Coordinates;
import com.where2beer.ws.common.helper.SpecificationHelper;
import com.where2beer.ws.common.model.search.SearchCriterion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RequiredArgsConstructor
public class BarSpecification implements Specification<Bar> {

    private final SearchCriterion criterion;

    @Override
    public Predicate toPredicate(Root<Bar> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criterion.getColumn().equals("coordinates")) {
            var coordinates = criterion.getValue().split(",");
            if (coordinates.length == 3) {
                var latitude = Math.toRadians(Double.parseDouble(coordinates[0]));
                var longitude = Math.toRadians(Double.parseDouble(coordinates[1]));
                var radius = Double.parseDouble(coordinates[2]);
                var center = new Coordinates(latitude, longitude);

                List<Coordinates> minAndMaxCoordinates = CoordinateHelper.calculateMinAndMax(center, radius);
                var address = root.get("address");
                var min = minAndMaxCoordinates.get(0);
                var max = minAndMaxCoordinates.get(1);
                return builder.and(
                        builder.between(address.get("latitudeInRad"), min.getLatitude(), max.getLatitude()),
                        builder.between(address.get("longitudeInRad"), min.getLongitude(), max.getLongitude()),
                        builder.le(this.haversine(address, builder, center.getLatitude(), center.getLongitude()), radius / CoordinateHelper.EARTH_RADIUS)
                );
            }
        }

        return SpecificationHelper.predicate(criterion, root, query, builder);
    }

    private Expression<? extends Number> haversine(Path<Object> root, CriteriaBuilder cb, double lat, double lng) {
        var first = sin(cb, cb.literal(lat));
        var second = sin(cb, root.get("latitudeInRad"));
        var third = cos(cb, cb.literal(lat));
        var fourth = cos(cb, root.get("latitudeInRad"));
        var fifth = cos(cb, cb.diff(root.get("longitudeInRad"), cb.literal(lng)));

        return cb.function(
                "ACOS",
                Double.class,
                cb.sum(
                        cb.prod(first, second),
                        cb.prod(third, cb.prod(fourth, fifth))
                )
        );
    }

    private Expression<Double> sin(CriteriaBuilder cb, Expression<?> expression) {
        return cb.function("SIN", Double.class, expression);
    }

    private Expression<Double> cos(CriteriaBuilder cb, Expression<?> expression) {
        return cb.function("COS", Double.class, expression);
    }
}
