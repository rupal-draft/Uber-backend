package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.exceptions.RuntimeConflictException;
import com.project.uber.Uber.services.DistanceCalculationService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceCalculationOSRMImpl implements DistanceCalculationService {

    private static final String OSRM_API_BASE_URL = "https://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point src, Point des) {

        try{
            String uri = src.getX()+","+src.getY()+";"+des.getX()+","+des.getY();

            OSRMResponseDto osrmResponse = RestClient
                    .builder()
                    .baseUrl(OSRM_API_BASE_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDto.class);

            return osrmResponse.getRoutes().get(0).getDistance() / 1000.0;
        } catch (Exception e){
            throw new RuntimeConflictException("Error getting data from OSRM "+e.getLocalizedMessage());
        }
    }
}

class OSRMResponseDto{
    List<OSRMRoutes> routes;

    public List<OSRMRoutes> getRoutes() {
        return routes;
    }

    public void setRoutes(List<OSRMRoutes> routes) {
        this.routes = routes;
    }
}

class OSRMRoutes{
    private Double distance;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
