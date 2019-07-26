/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author david.rodriguezusam
 */
public class GenericDao {

    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();

    public Object insertarEntidad(Object obj) {
        try {
            em.persist(obj);
            em.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public String modificarEntidad(Object obj) {
        String mensaje = "";
        try {
            em.merge(obj);
            mensaje = "exito";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "error";
        }
        return mensaje;
    }
}
