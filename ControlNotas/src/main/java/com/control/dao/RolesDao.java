package com.control.dao;

import com.control.entity.Roles;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alexander.emesticaus
 */
public class RolesDao {

    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();

    private List<Roles> listaRoles;
    private Roles roles;

    public List<Roles> selectAllRoles() {
        try {
            listaRoles = new ArrayList<Roles>();
            listaRoles = em.createNamedQuery("Roles.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaRoles;
    }

    public Roles selectByIdRol(Roles id) {
        try {
            roles = (Roles) em.createNamedQuery("Roles.findByIdRol").setParameter("idRol", id.getIdRol()).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }

    public String deleteRol(Roles id) {
        String msg = null;
        try {
            em.remove(em.find(Roles.class, id.getIdRol()));
            msg = "Eliminado Correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error Al Eliminar";
        }
        return msg;
    }
}
