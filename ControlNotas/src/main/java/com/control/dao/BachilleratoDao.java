/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entity.Colegio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author david.rodriguezusam
 */
public class BachilleratoDao {

    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();

    private List<Colegio> bachilleratolist;
    private Colegio bachillerato;

    public List<Colegio> bachillertaoAll() {
        try {
            bachilleratolist = new ArrayList<Colegio>();
            bachilleratolist = em.createNamedQuery("Bachillerato.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bachilleratolist;
    }

    public Colegio bachilleratoById(Colegio b) {
        try {
            bachillerato = new Colegio();
            bachillerato = (Colegio) em.createNamedQuery("Bachillerato.findByIdBachillerato").setParameter("idBachillerato", b.getIdColegio()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bachillerato;
    }
    
    public String eliminarBachillerato(Colegio b){
        String mensaje = "";
        try {
            em.remove(em.find(Colegio.class, b.getIdColegio()));
            mensaje = "Bachillerato eliminado";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error B0001";
        }
        return mensaje;
    }
}
