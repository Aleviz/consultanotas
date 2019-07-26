/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entity.Matricula;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author david.rodriguezusam
 */
public class MatriculaDao {

    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();

    private List<Matricula> matriculaList;
    private Matricula matricula;

    public List<Matricula> matriculaAll() {
        try {
            matriculaList = new ArrayList<Matricula>();
            matriculaList = em.createNamedQuery("Matricula.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matriculaList;
    }

    public Matricula matriculaById(Matricula m) {
        try {
            matricula = new Matricula();
            matricula = (Matricula) em.createNamedQuery("Matricula.findByIdMatricula").setParameter("idMatricula", m.getIdMatricula()).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matricula;
    }
    
     public Matricula porAlumno(Matricula m) {
        try {
            matricula = new Matricula();
            matricula = (Matricula) em.createNamedQuery("Matricula.findByIdMatricula").setParameter("idAlumno", m.getIdAlumno()).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matricula;
    }

    public String eliminarMatricula(Matricula m) {
        String mensaje = "";
        try {
            em.remove(em.find(Matricula.class, m.getIdMatricula()));
            mensaje = "Matricula eliminada";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error M0001";
        }
        return mensaje;
    }
}
