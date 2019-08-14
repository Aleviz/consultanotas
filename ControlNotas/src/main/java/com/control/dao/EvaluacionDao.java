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
            evaluacionList = em.createNativeQuery("select id_evaluacion, id_alumno, id_profesor, eva1, eva2, eva3, eva4, proEva1, "
                    + "eva5, eva6, eva7, eva8, proEva2, "
                    + "eva9, eva10, eva11, eva12, proEva3, proEvato from evaluacion e "
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
            evaluacion = new Evaluacion();
            evaluacion = (Evaluacion) em.createNativeQuery(sql, Evaluacion.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return evaluacion;
    }
    public List<Evaluacion> por1Alumno(int idAlumno) {
        String sql = "SELECT * FROM bdcne.evaluacion where id_alumno=" + idAlumno;
        try {
            evaluacionList = new ArrayList<Evaluacion>();
            evaluacionList = em.createNativeQuery(sql, Evaluacion.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return evaluacionList;
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
