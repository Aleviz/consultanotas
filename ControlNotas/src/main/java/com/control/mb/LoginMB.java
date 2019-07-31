/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.mb;

import com.control.dao.AccesoDao;
import com.control.dao.UsuariosDao;
import com.control.entity.Alumnos;
import com.control.entity.Empleados;
import com.control.entity.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author manuel.rodriguezusam
 */
@ManagedBean
@RequestScoped
public class LoginMB {

    //ENTITY
    private Usuarios usuario;
    private String rol;
    private Alumnos alumno;
    private Empleados empleado;

    //LIST
    private List<Usuarios> usuarioList;

    //DAOS
    private UsuariosDao usuarioDao;
    private AccesoDao acces;

    //PERMISOS Y CONDICIONES
    private boolean esAlumno;
    private boolean esProfesor;
    private boolean esDirector;
    private boolean esSecretaria;
    private boolean sonEmpleados;
    private boolean esSubDirector;
    private boolean direcXSub;

    @PostConstruct
    public void init() {

        //ENTITY
        usuario = new Usuarios();
        alumno = new Alumnos();
        empleado = new Empleados();

        //LIST
        usuarioList = new ArrayList<Usuarios>();

        //DAOS
        usuarioDao = new UsuariosDao();
        acces = new AccesoDao();

        //PERMISOS Y CONDICIONES
        esAlumno = false;
        esProfesor = false;
        esDirector = false;
        esSecretaria = false;
        sonEmpleados = false;
        esSubDirector = false;
        direcXSub = false;
    }

    public String logear() {
        String usuariio = "";
        String pass = "";

        usuariio = usuario.getUsuario();
        pass = usuario.getPass();
        System.out.println("usuario en login= " + usuariio + " pass en login= " + pass);

        usuario = acces.logeado(usuariio, pass);

        if (usuario != null) {
            rol = usuario.getIdRol().getRol();
            System.out.println("USUARIO LOGEADO");

            if (rol.equalsIgnoreCase("Director")) {
                esAlumno = false;
                esProfesor = false;
                esDirector = true;
                esSecretaria = false;
                sonEmpleados = true;
                esSubDirector = false;
                direcXSub = true;

                usuario.getEmpleadosList();
                if (usuario.getEmpleadosList().get(0) != null) {
                    empleado = usuario.getEmpleadosList().get(0);
                }

            } else if (rol.equalsIgnoreCase("Sub-Director")) {
                esAlumno = false;
                esProfesor = false;
                esDirector = false;
                esSecretaria = false;
                sonEmpleados = true;
                esSubDirector = true;
                direcXSub = true;

                usuario.getEmpleadosList();
                if (usuario.getEmpleadosList().get(0) != null) {
                    empleado = usuario.getEmpleadosList().get(0);
                }

            } else if (rol.equalsIgnoreCase("Empleado")) {
                esAlumno = false;
                esProfesor = false;
                esDirector = false;
                esSecretaria = true;
                sonEmpleados = true;
                esSubDirector = false;
                direcXSub = false;

                usuario.getEmpleadosList();
                if (usuario.getEmpleadosList().get(0) != null) {
                    empleado = usuario.getEmpleadosList().get(0);
                }

            } else if (rol.equalsIgnoreCase("Docente")) {
                esAlumno = false;
                esProfesor = true;
                esDirector = false;
                esSecretaria = false;
                sonEmpleados = true;
                esSubDirector = false;
                direcXSub = false;

                usuario.getEmpleadosList();
                if (usuario.getEmpleadosList().get(0) != null) {
                    empleado = usuario.getEmpleadosList().get(0);
                }

            } else if (rol.equalsIgnoreCase("Alumno")) {
                esAlumno = true;
                esProfesor = false;
                esDirector = false;
                esSecretaria = false;
                sonEmpleados = false;
                esSubDirector = false;
                direcXSub = false;

                usuario.getAlumnosList();

                if (usuario.getAlumnosList().get(0) != null) {
                    alumno = usuario.getAlumnosList().get(0);
                }
            }
            return "prueba.xhtml";
        }

        usuario = new Usuarios();
        return "acceso.xhtml";

    }

    public AccesoDao getAcces() {
        return acces;
    }

    public void setAcces(AccesoDao acces) {
        this.acces = acces;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public List<Usuarios> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuarios> usuarioList) {
        this.usuarioList = usuarioList;
    }


    public UsuariosDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuariosDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isEsAlumno() {
        return esAlumno;
    }

    public void setEsAlumno(boolean esAlumno) {
        this.esAlumno = esAlumno;
    }

    public boolean isEsProfesor() {
        return esProfesor;
    }

    public void setEsProfesor(boolean esProfesor) {
        this.esProfesor = esProfesor;
    }

    public boolean isEsDirector() {
        return esDirector;
    }

    public void setEsDirector(boolean esDirector) {
        this.esDirector = esDirector;
    }

    public boolean isEsSecretaria() {
        return esSecretaria;
    }

    public void setEsSecretaria(boolean esSecretaria) {
        this.esSecretaria = esSecretaria;
    }

    public boolean isSonEmpleados() {
        return sonEmpleados;
    }

    public void setSonEmpleados(boolean sonEmpleados) {
        this.sonEmpleados = sonEmpleados;
    }

    public boolean isEsSubDirector() {
        return esSubDirector;
    }

    public void setEsSubDirector(boolean esSubDirector) {
        this.esSubDirector = esSubDirector;
    }

    public boolean isDirecXSub() {
        return direcXSub;
    }

    public void setDirecXSub(boolean direcXSub) {
        this.direcXSub = direcXSub;
    }
    
    

}
