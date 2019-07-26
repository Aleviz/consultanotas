package com.control.dao;

import com.control.entity.Empleados;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alexander.emesticaus
 */
public class EmpleadosDao {

    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();

    private List<Empleados> listaEmpleados;
    private Empleados empleados;

    public List<Empleados> selectAllEmpleados() {
        try {
            listaEmpleados = new ArrayList<Empleados>();
            listaEmpleados = em.createNamedQuery("Empleados.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }

    public Empleados selectByIdEmpleado(Empleados id) {
        try {
            empleados = (Empleados) em.createNamedQuery("Empleados.findByIdEmpleado").setParameter("idEmpleado", id.getIdEmpleado()).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public String deleteEmpleado(Empleados id) {
        String msg = null;
        try {
            em.remove(em.find(Empleados.class, id.getIdEmpleado()));
            msg = "Eliminado Correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error Al Eliminar";
        }
        return msg;
    }
}
