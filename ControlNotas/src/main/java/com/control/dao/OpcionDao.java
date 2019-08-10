/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entity.Materias;
import com.control.entity.OpcionEspe;
import com.control.entity.Opciones;
import java.util.ArrayList;
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
    private List<Opciones> opXEspList;
    private List<OpcionEspe> opEspList;
    private List<OpcionEspe> opEsList;

    public List<Opciones> allOpcion() {
        try {
            opList = em.createNamedQuery("Opciones.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opList;
    }
    
    public List<Opciones> obtenerOpcionXEspecialidad(Integer idOpcion){
        String sql="select * from opciones where id_opcion_espe="+idOpcion+";";
        try {
            opXEspList = new ArrayList<Opciones>();
            opXEspList = em.createNativeQuery(sql, Opciones.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opXEspList;
    }
    
    public List<OpcionEspe> allOpcionEspe(){
        try {
            opEspList = new ArrayList<OpcionEspe>();
            opEspList = em.createNamedQuery("OpcionEspe.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opEspList;
    }
    
    public List<Opciones> obtenerOpcionesXEspe(int idOpcionEspe){
        try {
            opList = new ArrayList<Opciones>();
            opList = em.createNamedQuery("Opciones.findAllxOpcionEspe").setParameter("idOpcionEspe", idOpcionEspe).getResultList();
            
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

