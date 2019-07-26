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
public class RegistroMatriculaMB {

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
}
