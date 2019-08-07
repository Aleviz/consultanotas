/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entity.Especialidad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel.rodriguezusam
 */
@Stateless
public class EspecialidadesDao {
    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();
    
    private List<Especialidad> especialidadList;
    private Especialidad especialidad;
    
    public List<Especialidad> allEspecialidades(){
        try {
            especialidadList = em.createNamedQuery("Especialidades.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return especialidadList;
    }
    
    
    public Especialidad porEspecialidad(int idEspecialidad){
        try {
            especialidad = (Especialidad)em.createNativeQuery("SELECT e.id_especilidad, e.especialidad, e.descripcion FROM especialidades e WHERE id_especialidades ="+idEspecialidad, Especialidad.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return especialidad;
    }
    
    public String eliminarEspecialidad(Especialidad ep){
         String mensaje = "";
        try {
            em.remove(em.find(Especialidad.class, ep.getIdEspecialidad()));
           mensaje = "Exito";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensaje;
    }
    
    
    
    
    
}
