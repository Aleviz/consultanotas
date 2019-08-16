/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entity.Alumnos;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel.rodriguezusam
 */
public class AlumnosDao {
    
    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();
    
    private Alumnos alumnos;
    private List<Alumnos> alumnosList;
    private List<Alumnos> alumnosXGradoList;
    
    public List<Alumnos> allAlumnos(){
        try {
            alumnosList = em.createNamedQuery("Alumnos.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alumnosList;
    }
    
    public Alumnos selectByIdAlumno(Alumnos id){
        try {
            alumnos = (Alumnos) em.createNamedQuery("Alumnos.findByIdAlumno").setParameter("idAlumno", id.getIdAlumno()).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alumnos;
    }
        
    public Alumnos porAlumnos(int idAlumno){
        try {
            alumnos = (Alumnos)em.createNamedQuery("Alumnos.findByIdAlumno").setParameter("idAlumno", idAlumno).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alumnos;
    }
    
    public List<Alumnos> porEncargado (int encargado){
        String sql ="SELECT a.id_alumno, a.primer_nombre, a.segundo_nombre, a.primer_apellido, a.segundo_apellido, a.fecha_nacimiento, a.telefono, a.direccion, a.carnet, a.id_encargado, a.id_usuario  FROM Alumnos a WHERE a.id_encargado="+encargado;
        try {
            alumnosList = em.createNativeQuery(sql).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alumnosList;
    }
    
     public List<Alumnos> porUsuario (int usuario){
        String sql ="SELECT a.id_alumno, a.primer_nombre, a.segundo_nombre, a.primer_apellido, a.segundo_apellido, a.fecha_nacimiento, a.telefono, a.direccion, a.carnet, a.id_encargado, a.id_usuario  FROM Alumnos a WHERE a.id_usuario="+usuario;
        try {
            alumnosList = em.createNativeQuery(sql).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alumnosList;
    }
     
     public String eliminarAlumno(Alumnos a){
         String mensaje ="";
         try {
             em.remove(em.find(Alumnos.class,a.getIdAlumno() ));
             mensaje ="Exito";
         } catch (Exception e) {
             e.printStackTrace();
         }
         return mensaje;
     }  
}
