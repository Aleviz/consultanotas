package com.control.dao;

import com.control.entity.Evaluacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author david.rodriguezusam
 */
public class EvaluacionDao {

    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();

    private List<Evaluacion> evaluacionList;
    private Evaluacion evaluacion;

    public List<Evaluacion> evaluacionAll() {
        try {
            evaluacionList = new ArrayList<Evaluacion>();
            evaluacionList = em.createNamedQuery("Evaluacion.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evaluacionList;
    }

    public List<Evaluacion> evaluacionXMateria(int idMateria) {
        try {
            evaluacionList = new ArrayList<Evaluacion>();
            evaluacionList = em.createNativeQuery("select e.id_evaluacion, e.id_alumno, e.id_profesor, e.eva1, e.eva2, e.eva3, e.eva4,e.proEva1, "
                    + "e.eva5, e.eva6, e.eva7, e.eva8,e.proEva2, "
                    + "e.eva9, e.eva10, e.eva11, eva12, e.proEva3, e.proEvato from evaluacion e "
                    + "inner join empleados em on e.id_profesor = em.id_empleado where em.id_materia = " + idMateria + ";").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evaluacionList;
    }

    public Evaluacion evaluacionById(Evaluacion ev) {
        try {
            evaluacion = new Evaluacion();
            evaluacion = (Evaluacion) em.createNamedQuery("Evaluacion.findByIdEvaluacion").setParameter("idEvaluacion", ev.getIdEvaluacion()).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evaluacion;
    }

    public Evaluacion porAlumnos(int idAlumno, int idProfesor) {
        String sql = "SELECT * FROM bdcne.evaluacion where id_alumno=" + idAlumno + " and id_profesor=" + idProfesor;
        try {
            evaluacion = (Evaluacion) em.createNativeQuery(sql, Evaluacion.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evaluacion;
    }

    public String eliminarEvaluacion(Evaluacion ev) {
        String mensaje = "";
        try {
            em.remove(em.find(Evaluacion.class, ev.getIdEvaluacion()));
            mensaje = "Evaluacion eliminada";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error E0001";
        }
        return mensaje;
    }
}
