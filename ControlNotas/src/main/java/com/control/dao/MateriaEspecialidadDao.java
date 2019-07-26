/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entity.MateriaEspecialidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel.rodriguezusam
 */
public class MateriaEspecialidadDao {
    
    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();
    
    private MateriaEspecialidad matEsp;
    private List<MateriaEspecialidad> matEspList;
    
    public List<MateriaEspecialidad> allMatEspe(){
        try {
            matEspList = em.createNamedQuery("MateriaEspecialidad.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matEspList;
    }
    
    public MateriaEspecialidad porMatEsp (int idMatEsp){
        try {
            matEsp = (MateriaEspecialidad)em.createNativeQuery("SELECT m.id_mat_esp , m.id_materia, m.id_especialidad FROM materia_especialidad m WHERE m.id_mat_esp ="+idMatEsp, MateriaEspecialidad.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matEsp;
    }
    
    public List<MateriaEspecialidad> porMateria(int idMat){
        try {
            matEspList  = em.createNativeQuery("SELECT m.id_mat_esp , m.id_materia, m.id_especialidad FROM materia_especialidad m WHERE m.id_materia.id_materia ="+idMat, MateriaEspecialidad.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matEspList;
    }
    
       public List<MateriaEspecialidad> porEspecialidad(int idEspe){
        try {
            matEspList  = em.createNativeQuery("SELECT m.id_mat_esp , m.id_materia, m.id_especialidad FROM materia_especialidad m WHERE m.id_especialidad.id_especialidad ="+idEspe, MateriaEspecialidad.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matEspList;
    }
       
       public String eliminarMatEspe(MateriaEspecialidad matE){
           String mensaje ="";
           try {
               em.remove(em.find(MateriaEspecialidad.class, matE.getIdMatEsp()));
               mensaje ="Exito";
           } catch (Exception e) {
               e.printStackTrace();
           }
           return mensaje;
       }
    
}
