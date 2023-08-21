package org.example.planet;

import org.example.exception.NullOutputException;

import java.util.List;

public interface PlanetService {
    void createPlanet(Planet planet) throws NullOutputException;
    void deletePlanet(Planet planet) throws  NullOutputException;
    void getPlanet (String planetId) throws NullOutputException;
    void updatePlanet(String planetId, Planet planet) throws NullOutputException;
    List<Planet> getAllPlanets();
}
