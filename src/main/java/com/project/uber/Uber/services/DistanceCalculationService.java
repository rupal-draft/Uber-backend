package com.project.uber.Uber.services;

import org.locationtech.jts.geom.Point;

public interface DistanceCalculationService {

    double calculateDistance(Point src, Point des);
}
