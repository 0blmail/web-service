package com.where2beer.ws.bar.helper;

import com.where2beer.ws.bar.model.Coordinates;

import java.util.List;

public class CoordinateHelper {

    public static final double EARTH_RADIUS = 6371.01;

    CoordinateHelper() {
    }

    public static List<Coordinates> calculateMinAndMax(Coordinates center, double radius) {
        double r = radius / EARTH_RADIUS;

        double lat = center.getLatitude();
        double lon =center.getLongitude();

        double minLatitude = lat - r;
        double maxLatitude = lat + r;
        double deltaLongitude = Math.asin(Math.sin(r) / Math.cos(lon));
        double minLongitude = lon - deltaLongitude;
        double maxLongitude = lon + deltaLongitude;

        return List.of(
                new Coordinates(minLatitude, minLongitude),
                new Coordinates(maxLatitude, maxLongitude)
        );
    }
}
