package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.services.DistanceCalculationService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceCalculationImpl implements DistanceCalculationService {
    @Override
    public double calculateDistance(Point src, Point des) {
        return 0;
    }
}
