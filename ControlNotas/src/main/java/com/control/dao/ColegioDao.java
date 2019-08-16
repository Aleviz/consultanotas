
package com.control.dao;

import com.control.entity.Colegio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author david.rodriguezusam
 */
public class ColegioDao {
    EntityManagerFactory f =  Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();
    
    private List<Colegio> colegioList;
    private Colegio colegio;
    
    public List<Colegio> colegioAll(){
        try {
            colegioList = new ArrayList<Colegio>();
            colegioList = em.createNamedQuery("Colegio.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return colegioList;
    }
    
    public Colegio colegioById(Colegio c){
        try {
            colegio = new Colegio();
            colegio = (Colegio)em.createNamedQuery("Colegio.findByIdColegio").setParameter("idColegio", c.getIdColegio()).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return colegio;
    }
    
    public String eliminarColegio(Colegio c){
        String mensaje = "";
        try {
            em.remove(em.find(Colegio.class, c.getIdColegio()));
            mensaje = "colegio eliminado";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "error C0001";
        }
        return mensaje;
    }
}
