/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entity.Opciones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel.rodriguezusam
 */
public class OpcionDao {
     EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();

    private Opciones op;
    private List<Opciones> opList;

    public List<Opciones> allOpcion() {
        try {
            opList = em.createNamedQuery("Opcion.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opList;
    }
    
    public Opciones porOpcion(int opcion){
        String sql = "SELECT o.id_opcion, o.id_especialidad, o.seccion, o.año, o.descripcion FROM opcion o WHERE o.id_opcion ="+opcion;
        try {
            op = (Opciones)em.createNativeQuery(sql, Opciones.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return op;
    }

    
    public String eliminarOpcion(Opciones op){
        String mensaje ="";
        try {
            em.remove(em.find(Opciones.class, op.getIdOpcion()));
            mensaje = "Exito";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensaje;
    }
}

