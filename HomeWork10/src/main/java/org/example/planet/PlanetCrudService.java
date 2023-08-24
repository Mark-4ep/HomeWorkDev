package org.example.planet;

import org.example.exception.NullOutputException;
import org.example.hibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetCrudService implements PlanetService {
    HibernateUtil hibernateUtil = HibernateUtil.getInstance();

    @Override
    public void createPlanet(Planet planet) {
        if (!planet.getId().matches("^[A-Z0-9]*$")) {
            System.out.println("The planet ID must contain capital letters without special characters.");
        }
        else if (planet.getName().length() <= 1) {
            System.out.println("The name of the planet must contain at least one character.");
        }
        else {
            try(Session session = hibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();

                Planet existing = session.get(Planet.class, planet.getId());
                if (existing != null) {
                    System.out.println("The planet with id " + planet.getId() + " already exists.");
                }
                else {
                    session.persist(planet);
                    transaction.commit();
                }
            }
        }
    }

    @Override
    public void deletePlanet(Planet planet) throws NullOutputException {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            if (planet == null) {
                throw new NullOutputException("No planet found with the name :" + planet.getName());
            } else {
                Planet existing = session.get(Planet.class, planet.getId());

                session.remove(existing);
                transaction.commit();

                System.out.println("Planet " + planet.getName() + "removed!");
            }
        }
    }

    @Override
    public Planet getPlanet(String planetId) throws NullOutputException {
        try(Session session = hibernateUtil.getSessionFactory().openSession()) {
            Planet planet = session.get(Planet.class, planetId);
            if (planet == null) {
                throw new NullOutputException("No planet found with the id :" + planetId);
            } else {
                return planet;
            }
        }
    }

    @Override
    public void updatePlanet(String planetId, Planet planet) throws NullOutputException {

        try(Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Planet oldPlanet = session.get(Planet.class, planetId);

            if (planet == null) {
                throw new NullOutputException("No planet found with the id :" + planetId);
            } else {
                oldPlanet.setName(planet.getName());
                oldPlanet.setId(planetId);
                session.persist(oldPlanet);

                transaction.commit();
                System.out.println("Successfully updated the planet");
            }
        }
    }

    @Override
    public List<Planet> getAllPlanets() {
        Session session = hibernateUtil.getSessionFactory().openSession();
        List<Planet> planets = session.createQuery("from Planet", Planet.class).list();

        System.out.println("passengers = " + planets);
        session.close();

        return planets;
    }
}
