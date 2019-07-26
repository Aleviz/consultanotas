/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entity.TipoMatricula;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel.rodriguezusam
 */
public class TipoMatriculaDao {

    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();

    private TipoMatricula tipo;
    private List<TipoMatricula> tipoList;

    public List<TipoMatricula> allMatricula() {
        try {
            tipoList = em.createNamedQuery("Matricula.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipoList;
    }

    public TipoMatricula porTipo(int idTipo) {
        try {
            tipo = (TipoMatricula) em.createNativeQuery("SELECT m.id_materia, m.materia, m.descripccion, m.id_profesor FROM materias m WHERE m.id_materia =" + idTipo, TipoMatricula.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipo;
    }

    public String eliminarMaterias(TipoMatricula tm) {
        String mensaje = "";
        try {
            em.remove(em.find(TipoMatricula.class, tm.getIdTipoMat()));
            mensaje = "Exito";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensaje;
    }
}
