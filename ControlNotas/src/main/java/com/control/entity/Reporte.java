/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.entity;

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

    
    

}