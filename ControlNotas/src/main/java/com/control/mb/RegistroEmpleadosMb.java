package com.control.mb;

import com.control.dao.EmpleadosDao;
import com.control.dao.GenericDao;
import com.control.dao.UsuariosDao;
import com.control.entity.Empleados;
import com.control.entity.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexander.emesticaus
 */
@ManagedBean
@ViewScoped
public class RegistroEmpleadosMb {

    FacesMessage msg = new FacesMessage();

    private Empleados empleados;
    private Usuarios usuarios;

    private List<Empleados> listaEmpleados;
    private List<Usuarios> listaUsuarios;

    private GenericDao genericDao;
    private EmpleadosDao empleadosDao;
    private UsuariosDao usuariosDao;

    @PostConstruct
    public void init() {
        empleados = new Empleados();
        usuarios = new Usuarios();

        listaEmpleados = new ArrayList<Empleados>();
        listaUsuarios = new ArrayList<Usuarios>();

        genericDao = new GenericDao();
        empleadosDao = new EmpleadosDao();
        usuariosDao = new UsuariosDao();
    }

    public void insertarEmpleado() {
        genericDao.insertarEntidad(usuarios);
        empleados.setIdUsuario(usuarios);
        empleados = (Empleados)genericDao.insertarEntidad(empleados);
        if(null!=empleados){
            msg = new FacesMessage("Guardado Correctamente");
        }else{
            msg = new FacesMessage("Error Al Guardar");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void mostrarEmpleados() {
        listaEmpleados = empleadosDao.selectAllEmpleados();
    }

    public void seleccionarPorIdEmpleado(Empleados id) {
        empleados = empleadosDao.selectByIdEmpleado(id);
    }

    public void actualizarEmpleado() {
        String mensaje = genericDao.modificarEntidad(usuarios);
        msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void eliminarEmpleado(Empleados id) {
        String mensaje = empleadosDao.deleteEmpleado(id);
        msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void insertarUsuario() {
    }

    public void seleccionarPorIdUsuario() {
    }

    public void actualizarUsuario() {
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public List<Empleados> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleados> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public GenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public EmpleadosDao getEmpleadosDao() {
        return empleadosDao;
    }

    public void setEmpleadosDao(EmpleadosDao empleadosDao) {
        this.empleadosDao = empleadosDao;
    }

    public UsuariosDao getUsuariosDao() {
        return usuariosDao;
    }

    public void setUsuariosDao(UsuariosDao usuariosDao) {
        this.usuariosDao = usuariosDao;
    }
}
