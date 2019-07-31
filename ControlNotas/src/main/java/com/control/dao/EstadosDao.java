package com.control.dao;

import com.control.entity.Estados;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alexander.emesticaus
 */
public class EstadosDao {
    
    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();
    
    private List<Estados> listaUsuarios;
    
    public List<Estados> selectAllEstados(){
        try {
            listaUsuarios = new ArrayList<Estados>();
            listaUsuarios = em.createNamedQuery("Estados.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }
}
