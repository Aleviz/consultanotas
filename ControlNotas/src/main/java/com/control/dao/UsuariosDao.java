package com.control.dao;

import com.control.entity.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alexander.emesticaus
 */
public class UsuariosDao {

    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();

    private List<Usuarios> listaUsuarios;
    private Usuarios usuarios;

    public List<Usuarios> selectAllUsuarios() {
        try {
            listaUsuarios = new ArrayList<Usuarios>();
            listaUsuarios = em.createNamedQuery("Usuarios.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    public Usuarios selectBtIdUsuarios(Usuarios id) {
        try {
            usuarios = (Usuarios) em.createNamedQuery("Usuarios.findByIdUsuario").setParameter("idUsuario", id.getIdUsuario()).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public String deleteUsuario(Usuarios id) {
        String msg = null;
        try {
            em.remove(em.find(Usuarios.class, id.getIdUsuario()));
            msg = "Eliminado Correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error Al Momento de Eliminar";
        }
        return msg;
    }
}
