/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.entity;

import java.math.BigDecimal;

/**
 *
 * @author manuel.rodriguezusam
 */
public class Reporte {
   
    private Integer matricula;
    private String aNombre;
    private String aApellido;
    private String opcion;
    private String seccion;
    //
    private String especialidad;
    private String materia;
    private BigDecimal pro1;
    private BigDecimal pro2;
    private BigDecimal pro3;
    private BigDecimal prof;

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

   

    public String getaNombre() {
        return aNombre;
    }

    public void setaNombre(String aNombre) {
        this.aNombre = aNombre;
    }

    public String getaApellido() {
        return aApellido;
    }

    public void setaApellido(String aApellido) {
        this.aApellido = aApellido;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public BigDecimal getPro1() {
        return pro1;
    }

    public void setPro1(BigDecimal pro1) {
        this.pro1 = pro1;
    }

    public BigDecimal getPro2() {
        return pro2;
    }

    public void setPro2(BigDecimal pro2) {
        this.pro2 = pro2;
    }

    public BigDecimal getPro3() {
        return pro3;
    }

    public void setPro3(BigDecimal pro3) {
        this.pro3 = pro3;
    }

    public BigDecimal getProf() {
        return prof;
    }

    public void setProf(BigDecimal prof) {
        this.prof = prof;
    }

    
    

}