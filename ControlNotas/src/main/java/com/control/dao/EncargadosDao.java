package com.control.dao;

import com.control.entity.Alumnos;
import com.control.entity.Encargados;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alexander.emesticaus
 */
@Stateless
public class EncargadosDao {

    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();

    private List<Encargados> listaEncargados;
    private Encargados encargados;

    public List<Encargados> selectAllEncargados() {
        try {
            listaEncargados = new ArrayList<Encargados>();
            listaEncargados = em.createNamedQuery("Encargados.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEncargados;
    }
    
    public Encargados selectByIdAlumnoEncargado(int numId){
        String sql = "select e.id_encargado, e.primer_nombre, e.segundo_nombre, e.primer_apellido, e.segundo_apellido, e.dui, e.nit, e.telefono, e.direccion, e.edad from alumnos a inner join encargados e on a.id_encargado=e.id_encargado where a.id_alumno="+numId;
        try {
            encargados = (Encargados)em.createNamedQuery(sql, Encargados.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encargados;
    }

    public Encargados selectByIdEncargado(Encargados id) {
        try {
            encargados = (Encargados) em.createNamedQuery("Encargados.findByIdEncargado").setParameter("idEncargado", id.getIdEncargado()).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encargados;
    }
    
    public String deleteEncargado(Encargados id) {
        String msg = null;
        try {
            em.remove(em.find(Encargados.class, id.getIdEncargado()));
            msg = "Eliminado Correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error Al Eliminar";
        }
        return msg;
    }
}
