/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entity.Materias;
import java.util.ArrayList;
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
    
    public List<Materias> seleccionarMateriasPorOpcEspe(int idOpcionEspe){
        try {
            materiasList = new ArrayList<Materias>();
            materiasList = em.createNamedQuery("Materias.findByOpcionEspecialidad").setParameter("opcionEspe", idOpcionEspe).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materiasList;
    }

    public List<Materias> allMaterias() {
        try {
            materiasList = em.createNamedQuery("Materias.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materiasList;
    }
    
    public List<Materias> porMaterias(String Materia){
        try {
            materiasList = em.createNativeQuery("SELECT m.id_materia, m.materia, m.descripccion, m.opcion_espe FROM materias m WHERE m.id_materia ="+Materia, Materias.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materiasList;
    }
    
    public List<Materias> porOpEs(int opEs){
        try {
            materiasList = em.createNativeQuery("SELECT m.id_materia, m.materia, m.descripccion, m.opcio_espe FROM materias m WHERE m.opcion_espe ="+opEs, Materias.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materiasList;
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
