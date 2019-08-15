/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.mb;

import com.control.dao.AccesoDao;
import com.control.dao.EvaluacionDao;
import com.control.dao.UsuariosDao;
import com.control.entity.Alumnos;
import com.control.entity.Empleados;
import com.control.entity.Evaluacion;
import com.control.entity.Usuarios;
import com.control.util.UtilVarios;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author manuel.rodriguezusam
 */
@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {

    @ManagedProperty(value = "#{RegistroNotasMb}")
    private RegistroNotasMb registro;

    //ENTITY
    private Usuarios usuario;
    private Integer rol;
    private int nEmpleado;
    private int nA;
    private Alumnos alumnos;
    private Empleados empleado;
    private List<Evaluacion> evaAlumno;
    private EvaluacionDao notasDao;

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
    private boolean noEstaLogeado;

    @PostConstruct
    public void init() {

        registro = new RegistroNotasMb();
        evaAlumno = new ArrayList<Evaluacion>();
        notasDao = new EvaluacionDao();

        //ENTITY
        usuario = new Usuarios();
        alumnos = new Alumnos();
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
        noEstaLogeado = false;
        llenarNotasEstudiante();
    }

    public String logear() {
        String usuariio = "";
        String pass = "";

        UtilVarios uv = new UtilVarios();
        String passEnMd5 = uv.convertToMd5(usuario.getPass());

        usuariio = usuario.getUsuario();
        pass = passEnMd5;
        System.out.println("usuario en login= " + usuariio + " pass en login= " + pass);

        usuario = acces.logeado(usuariio, pass);

        if (usuario != null) {
            rol = usuario.getIdRol().getIdRol();
            System.out.println("USUARIO LOGEADO");

            if (rol == 1) {
                esAlumno = false;
                esProfesor = false;
                esDirector = true;
                esSecretaria = false;
                sonEmpleados = true;
                esSubDirector = false;
                direcXSub = true;
                System.out.println("ES::::::::::::::::");

                usuario.getEmpleadosList();
//                if (usuario.getEmpleadosList().get(0) != null) {
//                    empleado = usuario.getEmpleadosList().get(0);
//                    System.out.println("ACA:::::::::::");
//                }
                System.out.println("END::::::::::::::::");

            } else if (rol == 2) {
                esAlumno = false;
                esProfesor = false;
                esDirector = false;
                esSecretaria = false;
                sonEmpleados = true;
                esSubDirector = true;
                direcXSub = true;
                System.out.println("sub::::::::::::::");
                usuario.getEmpleadosList();
//                if (usuario.getEmpleadosList().get(0) != null) {
//                    empleado = usuario.getEmpleadosList().get(0);
//                }

            } else if (rol == 4) {
                esAlumno = false;
                esProfesor = false;
                esDirector = false;
                esSecretaria = true;
                sonEmpleados = true;
                esSubDirector = false;
                direcXSub = false;

                usuario.getEmpleadosList();

//                if (usuario.getEmpleadosList().get(0) != null) {
//                    empleado = usuario.getEmpleadosList().get(0);
//                }
                return "index.xhtml";
            } else if (rol == 3) {
                esAlumno = false;
                esProfesor = true;
                esDirector = false;
                esSecretaria = false;
                sonEmpleados = true;
                esSubDirector = false;
                direcXSub = false;

                usuario.getEmpleadosList();
                empleado = usuario.getEmpleadosList().get(0);
                nEmpleado = empleado.getIdEmpleado();
                System.out.println("empleado docente " + empleado.getPrimerNombre());
                System.out.println("empleado " + nEmpleado);
//                if (usuario.getEmpleadosList().get(0) != null) {
//                    empleado = usuario.getEmpleadosList().get(0);
//                }
                return "index.xhtml";
            } else if (rol == 5) {
                esAlumno = true;
                esProfesor = false;
                esDirector = false;
                esSecretaria = false;
                sonEmpleados = false;
                esSubDirector = false;
                direcXSub = false;

                alumnos = usuario.getAlumnosList().get(0);
                setnA(alumnos.getIdAlumno());
                llenarNotasEstudiante();
//                if (usuario.getAlumnosList().get(0) != null) {
//                    alumno = usuario.getAlumnosList().get(0);
//                }
            }
            System.out.println("IR::::::::::");
            System.out.println("Alumno " + registro.getN());
            return "NotasAlumno.xhtml";
        } else {

            usuario = new Usuarios();
            return "Login.xhtml";
        }
    }

    public void llenarNotasEstudiante() {
        System.out.println("alumno en llenarNotas : " + nA);

        evaAlumno = notasDao.por1Alumno(nA);
    }

    public void logout() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + "/Login.xhtml");
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//        System.out.println("Number: "+usuario.getUsuario());
//        return "/Login.xhtml?faces-redirect=true";
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

    public Alumnos getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Alumnos alumnos) {
        this.alumnos = alumnos;
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

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
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

    public int getnEmpleado() {
        return nEmpleado;
    }

    public void setnEmpleado(int nEmpleado) {
        this.nEmpleado = nEmpleado;
    }

    public int getnA() {
        return nA;
    }

    public void setnA(int nA) {
        this.nA = nA;
    }

    public RegistroNotasMb getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroNotasMb registro) {
        this.registro = registro;
    }

    public List<Evaluacion> getEvaAlumno() {
        return evaAlumno;
    }

    public void setEvaAlumno(List<Evaluacion> evaAlumno) {
        this.evaAlumno = evaAlumno;
    }

    public EvaluacionDao getNotasDao() {
        return notasDao;
    }

    public void setNotasDao(EvaluacionDao notasDao) {
        this.notasDao = notasDao;
    }

}
