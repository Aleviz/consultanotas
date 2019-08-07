/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.mb;

import com.control.dao.GenericDao;
import com.control.dao.MatriculaDao;
import com.control.entity.Alumnos;
import com.control.entity.Matricula;
import com.control.entity.Opcion;
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
    private Opcion opcion;

    //LIST
    private List<Matricula> matriculaList;

    //INTEGER
    private int idAlumno;
    private int idTipo;
    private int idOpcion;

    //DAOS
    private GenericDao gd;
    private MatriculaDao matriculaDao;

    @PostConstruct
    public void init() {

        //ENTITY
        matricula = new Matricula();
        alumno = new Alumnos();
        tipoMatricula = new TipoMatricula();
        opcion = new Opcion();

        //LIST
        matriculaList = new ArrayList<Matricula>();

        //INTEGER
        idAlumno = 0;
        idTipo = 0;
        idOpcion = 0;

        //DAOS
        gd = new GenericDao();
        matriculaDao = new MatriculaDao();
        
        //METODOS
        allMatricula();
        porAlumno();
        porOpcion();
        porTipo();
    }
    
    public void guardar(){
    
        alumno.setIdAlumno(idAlumno);
        matricula.setIdAlumno(alumno);
        
        tipoMatricula.setIdTipoMat(idTipo);
        matricula.setIdTipo(tipoMatricula);
        
        opcion.setIdOpcion(idOpcion);
        matricula.setIdOpcion(opcion);
        
        matricula = (Matricula)gd.insertarEntidad(matricula);
    }
    
    public void allMatricula(){
        matriculaList = matriculaDao.matriculaAll();
    }
    
    public void porAlumno(){
        matricula =  matriculaDao.porAlumno(idAlumno);
    }
    
    public void porOpcion(){
        matricula = matriculaDao.porOpcion(idOpcion);
    }

    public void porTipo(){
        matricula = matriculaDao.porTipo(idTipo);
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

<<<<<<< HEAD
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Encargados getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargados encargado) {
        this.encargado = encargado;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(Integer idEncargado) {
        this.idEncargado = idEncargado;
    }

    public Object[] getId() {
        return id;
    }

    public void setId(Object[] id) {
        this.id = id;
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
=======
    public List<Matricula> getMatriculaList() {
        return matriculaList;
>>>>>>> parent of f2c08fd... Merge branch 'FeaturesM' into Developer
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
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
