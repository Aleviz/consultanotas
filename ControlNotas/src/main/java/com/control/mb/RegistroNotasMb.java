/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.mb;

import com.control.dao.AlumnosDao;
import com.control.dao.EspecialidadesDao;
import com.control.dao.EvaluacionDao;
import com.control.dao.GenericDao;
import com.control.dao.OpcionesDao;
import com.control.entity.Alumnos;
import com.control.entity.Especialidad;
import com.control.entity.Evaluacion;
import com.control.entity.Opciones;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author david.rodriguezusam
 */
@ManagedBean
@ViewScoped
public class RegistroNotasMb {

    private List<Alumnos> alumnosList;
    private List<Especialidad> especialidadList;
    private List<Opciones> opcionList;
    private Opciones opcion;
    private Especialidad especialidad;
    private Alumnos alumno;
    private Evaluacion evaluacion;
    private List<Evaluacion> evaluacionList;
    private AlumnosDao alumnosDao;
    private EspecialidadesDao especialidadDao;
    private OpcionesDao opcionDao;
    private GenericDao genericDao;
    private EvaluacionDao notasDao;
    private Map<String, String > selectEspecialidad;
    private Map<String, String> selectGrado;
    private Map<String, String> selectAlumno;
    private Integer idEspecialidad;
    private Integer idGrado;
    private Integer idAlumno;
    
    @PostConstruct
    public void init(){
    alumnosList = new ArrayList<Alumnos>();
    especialidadList = new ArrayList<Especialidad>();
    opcionList = new ArrayList<Opciones>();
    opcion = new Opciones();
    especialidad = new Especialidad();
    alumno = new Alumnos();
    evaluacion = new Evaluacion();
    evaluacionList = new ArrayList<Evaluacion>();
    alumnosDao = new AlumnosDao();
    especialidadDao = new EspecialidadesDao();
    opcionDao = new OpcionesDao();
    genericDao = new GenericDao();
    notasDao = new EvaluacionDao();
    selectAlumno = new HashMap<String, String>();
    selectEspecialidad = new HashMap<String, String>();
    selectGrado = new HashMap<String, String>();
    llenarSelectAlumno();
    llenarSelectEspecialidad();
    llenarSelectGrado();
    llenarNotas();
    llenarDatosPersonales();
    }
    
    public void llenarSelectEspecialidad(){
        especialidadList = especialidadDao.allEspecialidades();
        System.out.println("-------------------"+especialidadList.size());
        for(Especialidad e : especialidadList){
            selectEspecialidad.put(e.getEspecialidad(), String.valueOf(e.getIdEspecialidad()));
        }
    }
    
    public void llenarDatosPersonales(){
        alumnosList = alumnosDao.allAlumnos();
    }
    
    public void llenarNotas(){
        evaluacionList = notasDao.evaluacionAll();
    }
    
    public void llenarSelectGrado(){
        opcionList = opcionDao.allOpcion();
        System.out.println("");
        for(Opciones o : opcionList){
            selectGrado.put(o.getDescripcion()+"-"+o.getSeccion(), String.valueOf(o.getIdOpcion()));
        }
    }
    
    public void llenarSelectAlumno(){
        alumnosList = alumnosDao.allAlumnos();
        for(Alumnos a : alumnosList){
            selectAlumno.put(a.getPrimerApellido()+" "+a.getSegundoApellido()+"-"+a.getCarnet(), String.valueOf(a.getIdAlumno()));
        }
    }

    public List<Alumnos> getAlumnosList() {
        return alumnosList;
    }

    public void setAlumnosList(List<Alumnos> alumnosList) {
        this.alumnosList = alumnosList;
    }

    public List<Especialidad> getEspecialidadList() {
        return especialidadList;
    }

    public void setEspecialidadList(List<Especialidad> especialidadList) {
        this.especialidadList = especialidadList;
    }

    public List<Opciones> getOpcionList() {
        return opcionList;
    }

    public void setOpcionList(List<Opciones> opcionList) {
        this.opcionList = opcionList;
    }

    public Opciones getOpcion() {
        return opcion;
    }

    public void setOpcion(Opciones opcion) {
        this.opcion = opcion;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    public AlumnosDao getAlumnosDao() {
        return alumnosDao;
    }

    public void setAlumnosDao(AlumnosDao alumnosDao) {
        this.alumnosDao = alumnosDao;
    }

    public EspecialidadesDao getEspecialidadDao() {
        return especialidadDao;
    }

    public void setEspecialidadDao(EspecialidadesDao especialidadDao) {
        this.especialidadDao = especialidadDao;
    }

    public OpcionesDao getOpcionDao() {
        return opcionDao;
    }

    public void setOpcionDao(OpcionesDao opcionDao) {
        this.opcionDao = opcionDao;
    }

    public GenericDao getGenericDao() {
        return genericDao;
    }

  public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public EvaluacionDao getNotasDao() {
        return notasDao;
    }

    public void setNotasDao(EvaluacionDao notasDao) {
        this.notasDao = notasDao;
    }

    public Map<String, String> getSelectEspecialidad() {
        return selectEspecialidad;
    }

    public void setSelectEspecialidad(Map<String, String> selectEspecialidad) {
        this.selectEspecialidad = selectEspecialidad;
    }

    public Map<String, String> getSelectGrado() {
        return selectGrado;
    }

    public void setSelectGrado(Map<String, String> selectGrado) {
        this.selectGrado = selectGrado;
    }

    public Map<String, String> getSelectAlumno() {
        return selectAlumno;
    }

    public void setSelectAlumno(Map<String, String> selectAlumno) {
        this.selectAlumno = selectAlumno;
    }

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }
    
    
}
