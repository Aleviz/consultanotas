/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entity.Opcion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel.rodriguezusam
 */
public class OpcionesDao {
     EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();

    private Opcion op;
    private List<Opcion> opList;

    public List<Opcion> allOpcion() {
        try {
            opList = em.createNamedQuery("Opcion.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opList;
    }
    
    public Opcion porOpcion(int opcion){
        String sql = "SELECT o.id_opcion, o.id_especialidad, o.seccion, o.año, o.descripcion FROM opcion o WHERE o.id_opcion ="+opcion;
        try {
            op = (Opcion)em.createNativeQuery(sql, Opcion.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return op;
    }

    
    public String eliminarOpcion(Opcion op){
        String mensaje ="";
        try {
            em.remove(em.find(Opcion.class, op.getIdOpcion()));
            mensaje = "Exito";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensaje;
    }
}

