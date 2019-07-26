/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.mb;

import com.control.dao.AlumnosDao;
import com.control.dao.BachilleratoDao;
import com.control.dao.GenericDao;
import com.control.dao.MatriculaDao;
import com.control.entity.Alumnos;
import com.control.entity.Bachillerato;
import com.control.entity.Matricula;
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
    private Alumnos alumnos;
    private Bachillerato bachillerato;

    //LIST
    private List<Matricula> matriculaList;

    //INTEGER
//    private int idMatricula;
    private int idAlumno;
    private int idBachillerato;

    //DAOS
    private MatriculaDao matriculaDao;
    private AlumnosDao alumnoDao;
    private BachilleratoDao bachilleratoDao;
    private GenericDao genericDao;

    @PostConstruct
    public void init() {

        //ENTITY
        matricula = new Matricula();
        alumnos = new Alumnos();
        bachillerato = new Bachillerato();

        //LIST
        matriculaList = new ArrayList<Matricula>();

        //INTEGER
//        idMatricula = 0;
        idAlumno = 0;
        idBachillerato = 0;

        //DAOS
        matriculaDao = new MatriculaDao();
        genericDao = new GenericDao();

        //METODOS
        allMatriculas();
        
    }

    public void registrarMatricula() {

        alumnos.setIdAlumno(idAlumno);
        matricula.setIdAlumno(alumnos);

        bachillerato.setIdBachillerato(idBachillerato);
        matricula.setIdBachillerato(bachillerato);

        matricula = (Matricula) genericDao.insertarEntidad(matricula);
    }

    public void allMatriculas() {
        matriculaList = matriculaDao.matriculaAll();
    }
    
    public void verPorId(Matricula m){
        Matricula ma = new Matricula();
        ma = matriculaDao.matriculaById(m);
    }
    
     public void verPorAlumno(Matricula m){
        Matricula ma = new Matricula();
        ma = matriculaDao.porAlumno(m);
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Alumnos getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Alumnos alumnos) {
        this.alumnos = alumnos;
    }

    public Bachillerato getBachillerato() {
        return bachillerato;
    }

    public void setBachillerato(Bachillerato bachillerato) {
        this.bachillerato = bachillerato;
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

    public int getIdBachillerato() {
        return idBachillerato;
    }

    public void setIdBachillerato(int idBachillerato) {
        this.idBachillerato = idBachillerato;
    }

    public MatriculaDao getMatriculaDao() {
        return matriculaDao;
    }

    public void setMatriculaDao(MatriculaDao matriculaDao) {
        this.matriculaDao = matriculaDao;
    }

    public AlumnosDao getAlumnoDao() {
        return alumnoDao;
    }

    public void setAlumnoDao(AlumnosDao alumnoDao) {
        this.alumnoDao = alumnoDao;
    }

    public BachilleratoDao getBachilleratoDao() {
        return bachilleratoDao;
    }

    public void setBachilleratoDao(BachilleratoDao bachilleratoDao) {
        this.bachilleratoDao = bachilleratoDao;
    }

    public GenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }
     
     

}
