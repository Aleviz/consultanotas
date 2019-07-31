/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entity.Bachillerato;
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

    private List<Bachillerato> bachilleratolist;
    private Bachillerato bachillerato;

    public List<Bachillerato> bachillertaoAll() {
        try {
            bachilleratolist = new ArrayList<Bachillerato>();
            bachilleratolist = em.createNamedQuery("Bachillerato.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bachilleratolist;
    }

    public Bachillerato bachilleratoById(Bachillerato b) {
        try {
            bachillerato = new Bachillerato();
            bachillerato = (Bachillerato) em.createNamedQuery("Bachillerato.findByIdBachillerato").setParameter("idBachillerato", b.getIdBachillerato()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bachillerato;
    }
    
    public String eliminarBachillerato(Bachillerato b){
        String mensaje = "";
        try {
            em.remove(em.find(Bachillerato.class, b.getIdBachillerato()));
            mensaje = "Bachillerato eliminado";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error B0001";
        }
        return mensaje;
    }
}
