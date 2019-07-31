/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entity.Materias;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel.rodriguezusam
 */
public class MateriasDao {

    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();

    private Materias materias;
    private List<Materias> materiasList;

    public List<Materias> allMaterias() {
        try {
            materiasList = em.createNamedQuery("Materias.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materiasList;
    }
    
    public Materias porMaterias(int idMateria){
        try {
            materias = (Materias)em.createNativeQuery("SELECT m.id_materia, m.materia, m.descripccion, m.id_profesor FROM materias m WHERE m.id_materia ="+idMateria, Materias.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materias;
    }

    public Materias porProfesor(int idProfe){
        try {
            materias = (Materias)em.createNativeQuery("SELECT m.id_materia, m.materia, m.descripccion, m.id_profesor FROM materias m WHERE m.id_profesor.id_empleado ="+idProfe, Materias.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materias;
    }
    
    public String eliminarMaterias(Materias m){
        String mensaje ="";
        try {
            em.remove(em.find(Materias.class, m.getIdMateria()));
            mensaje = "Exito";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensaje;
    }
}
