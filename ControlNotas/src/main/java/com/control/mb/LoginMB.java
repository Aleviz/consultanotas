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
    private boolean esADSS;
    private boolean esAdmin;
    private boolean noEstaLogeado;
    private boolean soloProfe;

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
        esADSS = false;
        esAdmin = false;
        soloProfe = false;
        noEstaLogeado = true;
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
                esAdmin = true;
                esDirector = true;
                esSubDirector = true;
                esProfesor = true;
                esSecretaria = true;
                esAlumno = false;
                direcXSub = true;
                sonEmpleados = true;
                esADSS = true;
                noEstaLogeado = false;
                soloProfe = false;

                System.out.println("ES ADMIN");
                usuario.getEmpleadosList();

                return "index.xhtml";

            } else if (rol == 2) {
                esAdmin = true;
                esDirector = true;
                esSubDirector = false;
                esProfesor = false;
                esSecretaria = false;
                esAlumno = false;
                direcXSub = true;
                sonEmpleados = true;
                esADSS = true;
                noEstaLogeado = false;
soloProfe = false;
                System.out.println("ES DIRECTOR");
                usuario.getEmpleadosList();

                return "index.xhtml";

            } else if (rol == 3) {
                esAdmin = true;
                esDirector = false;
                esSubDirector = true;
                esProfesor = false;
                esSecretaria = false;
                esAlumno = false;
                direcXSub = true;
                sonEmpleados = true;
                esADSS = true;
                noEstaLogeado = false;
soloProfe = false;
                System.out.println("ES SUBDIRECTOR");
                usuario.getEmpleadosList();

                return "index.xhtml";

            } else if (rol == 4) {
                esAdmin = true;
                esDirector = false;
                esSubDirector = false;
                esProfesor = true;
                esSecretaria = false;
                esAlumno = false;
                direcXSub = false;
                sonEmpleados = true;
                noEstaLogeado = false;
                esADSS = false;
soloProfe = true;
                System.out.println("ES DOCENTE");
                empleado = usuario.getEmpleadosList().get(0);
                nEmpleado = empleado.getIdEmpleado();

                return "controlNotas.xhtml";

            } else if (rol == 5) {
                esAdmin = true;
                esDirector = false;
                esSubDirector = false;
                esProfesor = false;
                esSecretaria = true;
                esAlumno = false;
                direcXSub = false;
                sonEmpleados = true;
                esADSS = true;
                noEstaLogeado = false;
soloProfe = false;
                System.out.println("ES SECRETARIA");
                usuario.getEmpleadosList();
                empleado = usuario.getEmpleadosList().get(0);
                nEmpleado = empleado.getIdEmpleado();
                System.out.println("empleado docente " + empleado.getPrimerNombre());
                System.out.println("empleado " + nEmpleado);
                return "index.xhtml";
            } else if (rol == 6) {
                esAdmin = false;
                esDirector = false;
                esSubDirector = false;
                esProfesor = false;
                esSecretaria = false;
                esAlumno = true;
                direcXSub = false;
                sonEmpleados = true;
                soloProfe = false;
                noEstaLogeado = false;
                System.out.println("ES ALUMNO");
                alumnos = usuario.getAlumnosList().get(0);
                setnA(alumnos.getIdAlumno());
                llenarNotasEstudiante();
                return "NotasAlumno.xhtml";
            }

        }

        usuario = new Usuarios();
        return "Login.xhtml";

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

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public boolean isEsADSS() {
        return esADSS;
    }

    public void setEsADSS(boolean esADSS) {
        this.esADSS = esADSS;
    }

    public boolean isNoEstaLogeado() {
        return noEstaLogeado;
    }

    public void setNoEstaLogeado(boolean noEstaLogeado) {
        this.noEstaLogeado = noEstaLogeado;
    }

    public boolean isSoloProfe() {
        return soloProfe;
    }

    public void setSoloProfe(boolean soloProfe) {
        this.soloProfe = soloProfe;
    }
    
    

}
