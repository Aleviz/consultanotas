/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.mb;

import com.control.dao.EmpleadosDao;
import com.control.dao.GenericDao;
import com.control.dao.MateriasDao;
import com.control.dao.MatriculaDao;
import com.control.entity.Alumnos;
import com.control.entity.Empleados;
import com.control.entity.Evaluacion;
import static com.control.entity.Evaluacion_.idProfesor;
import com.control.entity.Materias;
import com.control.entity.Matricula;
import com.control.entity.Opciones;
import com.control.entity.TipoMatricula;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author manuel.rodriguezusam
 */
@ManagedBean
@ViewScoped
public class RegistroMatriculaMb {

    //ENTITY
    private Matricula matricula;
    private Alumnos alumno;
    private TipoMatricula tipoMatricula;
    private Opciones opcion;

    private Evaluacion evaluacion;
    private List<Evaluacion> listEva;

    private Materias materias;

    private Empleados profesor;

    //LIST
    private List<Matricula> matriculaList;
    private List<Materias> materiasList;
    private List<Empleados> profesorList;

    //INTEGER
    Integer idAlumno;
    Integer idTipo;
    Integer idOpcion;
    Integer opcionEspe;

    Object[] id;

    //DAOS
    private GenericDao gd;
    private MatriculaDao matriculaDao;
    private MateriasDao materiasDao;
    private EmpleadosDao empDao;

    @PostConstruct
    public void init() {
        //ENTITY
        matricula = new Matricula();
        alumno = new Alumnos();
        tipoMatricula = new TipoMatricula();
        opcion = new Opciones();
        materias = new Materias();
        profesor = new Empleados();

        //LIST
        matriculaList = new ArrayList<Matricula>();
        materiasList = new ArrayList<Materias>();
        profesorList = new ArrayList<Empleados>();

        //DAOS
        gd = new GenericDao();
        matriculaDao = new MatriculaDao();
        materiasDao = new MateriasDao();
        empDao = new EmpleadosDao();
        //METODOS
        allMatricula();
//        porAlumno();
//        porOpcion();
//        porTipo();
    }

    public void guardar() {

        System.out.println("id " + idAlumno);
        alumno.setIdAlumno(idAlumno);
        matricula.setIdAlumno(alumno);

        tipoMatricula.setIdTipoMat(idTipo);
        matricula.setIdTipo(tipoMatricula);

        opcion.setIdOpcion(idOpcion);
        matricula.setIdOpcion(opcion);
        Matricula mat = new Matricula();

        profesorList = empDao.profXMateria(opcionEspe);

        System.out.println("profesorList.get(0)  " + profesorList.get(3).getIdEmpleado());
        System.out.println("todo se supone " + profesorList);

        System.out.println("Tipo matricula " + idTipo);
        System.out.println("Opcion  " + idOpcion);
        System.out.println("Especialidad " + opcionEspe);

        int x = profesorList.size();
        System.out.println("x " + x);

        for (int i = 0; i < x; i++) {
            evaluacion = new Evaluacion();
            System.out.println("ENTRO AL FOR");
            Empleados profe = profesorList.get(i);
            int idpr = profe.getIdEmpleado();

            System.out.println("idpr " + idpr);
            evaluacion.setIdAlumno(alumno);
            evaluacion.setIdProfesor(profe);
//            listEva.add(evaluacion);
            evaluacion = (Evaluacion) gd.insertarEntidad(evaluacion);
        }

        mat = (Matricula) gd.insertarEntidad(matricula);
        //-------------------------------------------------------

//        for (int i = 0; i <= x; i = i + 1) {
//
//                evaluacion.setIdAlumno(alumno);
//                evaluacion.setIdProfesor(profesor);
//                listEva.add(evaluacion);
//            }
//        
//        
// ----------------------------------------------------------------------
//      
    }

    public void allMatricula() {
        System.out.println("holaaaaaaaaaaaaa");

        matriculaList = matriculaDao.matriculaAll();
        System.out.println("matricula lista " + matriculaList.size());
    }

    public void porAlumno() {
        matricula = new Matricula();
        matricula = matriculaDao.porAlumno(idAlumno);
    }

    public void porOpcion() {
        matricula = new Matricula();
        matricula = matriculaDao.porOpcion(idOpcion);
    }

    public void porTipo() {
        matricula = new Matricula();
        matricula = matriculaDao.porTipo(idTipo);
    }

    //GETTER Y SETTER
    public Integer getIdAlumno() {
        if (idAlumno == null) {

            System.out.println("NULOOOO IDALUMNO");
        } else {
            System.out.println("No viene nulo");
        }
        return idAlumno;

    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdTipo() {

        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Alumnos getAlumno() {

        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public TipoMatricula getTipoMatricula() {
        return tipoMatricula;
    }

    public void setTipoMatricula(TipoMatricula tipoMatricula) {
        this.tipoMatricula = tipoMatricula;
    }

    public Opciones getOpcion() {
        return opcion;
    }

    public void setOpcion(Opciones opcion) {
        this.opcion = opcion;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public List<Evaluacion> getListEva() {
        return listEva;
    }

    public void setListEva(List<Evaluacion> listEva) {
        this.listEva = listEva;
    }

    public Materias getMaterias() {
        return materias;
    }

    public void setMaterias(Materias materias) {
        this.materias = materias;
    }

    public Empleados getProfesor() {
        return profesor;
    }

    public void setProfesor(Empleados profesor) {
        this.profesor = profesor;
    }

    public List<Materias> getMateriasList() {
        return materiasList;
    }

    public void setMateriasList(List<Materias> materiasList) {
        this.materiasList = materiasList;
    }

    public List<Empleados> getProfesorList() {
        return profesorList;
    }

    public void setProfesorList(List<Empleados> profesorList) {
        this.profesorList = profesorList;
    }

    public Integer getOpcionEspe() {
        return opcionEspe;
    }

    public void setOpcionEspe(Integer opcionEspe) {
        this.opcionEspe = opcionEspe;
    }

    public MateriasDao getMateriasDao() {
        return materiasDao;
    }

    public void setMateriasDao(MateriasDao materiasDao) {
        this.materiasDao = materiasDao;
    }

    public EmpleadosDao getEmpDao() {
        return empDao;
    }

    public void setEmpDao(EmpleadosDao empDao) {
        this.empDao = empDao;
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public GenericDao getGd() {
        return gd;
    }

    public void setGd(GenericDao gd) {
        this.gd = gd;
    }

    public MatriculaDao getMatriculaDao() {
        return matriculaDao;
    }

    public void setMatriculaDao(MatriculaDao matriculaDao) {
        this.matriculaDao = matriculaDao;
    }

}
