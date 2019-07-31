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
public class RegistroMatriculaMB {

    //ENTITY
    private Matricula matricula;
    private Alumnos alumno;
    private TipoMatricula tipoMatricula;
    private Opciones opcion;

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
        opcion = new Opciones();

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

////////////////////////////////////////////////////////////////////

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

    public List<Matricula> getMatriculaList() {
        return matriculaList;
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