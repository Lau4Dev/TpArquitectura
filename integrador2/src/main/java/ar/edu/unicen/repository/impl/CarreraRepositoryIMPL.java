package ar.edu.unicen.repository.impl;

import ar.edu.unicen.entity.Carrera;
import ar.edu.unicen.factory.JPAUtils;
import ar.edu.unicen.repository.CarreraRepository;
import jakarta.persistence.EntityManager;


public class CarreraRepositoryIMPL implements CarreraRepository {

    @Override
    public void create(Carrera carrera) {
        EntityManager em = JPAUtils.getEntityManager();
        em.getTransaction().begin();
        em.persist(carrera);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Carrera carrera) {
        EntityManager em = JPAUtils.getEntityManager();
        em.getTransaction().begin();
        em.merge(carrera);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAUtils.getEntityManager();
        em.getTransaction().begin();
        Carrera carrera = em.find(Carrera.class, id);
        if (carrera != null) {
            em.remove(carrera);
        }
        em.getTransaction().commit();
        em.close();

    }

}