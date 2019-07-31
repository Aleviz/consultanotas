package com.control.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author david.rodriguezusam
 */
public class GenericDao {

    private final EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    private final EntityManager em = f.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    public Object insertarEntidad(Object obj) {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return obj;
    }

    public String modificarEntidad(Object obj) {
        String mensaje = "";
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.flush();
            em.getTransaction().commit();
            mensaje = "Actualizado Correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            mensaje = "Error Al Actualizar";
        }
        return mensaje;
    }
}