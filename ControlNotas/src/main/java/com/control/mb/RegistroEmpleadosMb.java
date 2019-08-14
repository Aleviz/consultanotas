package com.control.mb;

import com.control.dao.EmpleadosDao;
import com.control.dao.EstadosDao;
import com.control.dao.GenericDao;
import com.control.dao.MateriasDao;
import com.control.dao.OpcionDao;
import com.control.dao.RolesDao;
import com.control.dao.UsuariosDao;
import com.control.entity.Empleados;
import com.control.entity.Estados;
import com.control.entity.Materias;
import com.control.entity.OpcionEspe;
import com.control.entity.Roles;
import com.control.entity.Usuarios;
import com.control.util.UtilVarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexander.emesticaus
 */
@ManagedBean
@SessionScoped
public class RegistroEmpleadosMb implements Serializable {

    FacesMessage msg = new FacesMessage();

    private Empleados empleados;
    private Usuarios usuarios;

    private List<Empleados> listaEmpleados;
    private List<Roles> listaRoles;
    private List<Estados> listaEstados;
    private List<Materias> listaMaterias;
    private List<Usuarios> listaUsuarios;
    private List<OpcionEspe> listaOpccionesEsp;

    private Map<String, String> seleccionarRol;
    private Map<String, String> seleccionarEstados;
    private Map<String, String> seleccionarMateria;
    private Map<String, String> seleccionarOpccionesEsp;
    private Map<String, String> selectMateriaByOpcionEsp;

    private Integer idRolVista;
    private Integer idEstadoVista;
    private Integer idMateriaVista;
    private int idOpccionesEspVista;

    private GenericDao genericDao;
    private EmpleadosDao empleadosDao;
    private RolesDao rolesDao;
    private MateriasDao materiasDao;
    private UsuariosDao usuariosDao;
    private EstadosDao estadosDao;
    private OpcionDao opcionDao;

    @PostConstruct
    public void init() {
        empleados = new Empleados();
        usuarios = new Usuarios();

        listaEmpleados = new ArrayList<Empleados>();
        listaRoles = new ArrayList<Roles>();
        listaMaterias = new ArrayList<Materias>();
        listaUsuarios = new ArrayList<Usuarios>();
        listaEstados = new ArrayList<Estados>();
        listaOpccionesEsp = new ArrayList<OpcionEspe>();

        genericDao = new GenericDao();
        empleadosDao = new EmpleadosDao();
        rolesDao = new RolesDao();
        materiasDao = new MateriasDao();
        usuariosDao = new UsuariosDao();
        estadosDao = new EstadosDao();
        opcionDao = new OpcionDao();

        seleccionarRol = new HashMap<String, String>();
        seleccionarEstados = new HashMap<String, String>();
        seleccionarMateria = new HashMap<String, String>();
        seleccionarOpccionesEsp = new HashMap<String, String>();
        selectMateriaByOpcionEsp = new HashMap<String, String>();

        mostrarEmpleados();
        mostrarUsuarios();
        llenarSelectRoles();
        llenarSelectEstados();
        llenarSelectEspecialidades();
        llenarSelectMateriasByOpcionEspecialidad();
    }

    public void insertarEmpleado() {

        Roles idRoles = new Roles();
        idRoles.setIdRol(idRolVista);
        usuarios.setIdRol(idRoles);

        Estados idEstados = new Estados();
        idEstados.setIdEstado(idEstadoVista);
        usuarios.setIdEstado(idEstados);

        genericDao.insertarEntidad(usuarios);

        empleados.setIdUsuario(usuarios);

        Materias idMaterias = new Materias();
        idMaterias.setIdMateria(idMateriaVista);
        empleados.setIdMateria(idMaterias);

        empleados = (Empleados) genericDao.insertarEntidad(empleados);
        if (null != empleados) {
            msg = new FacesMessage("Guardado Correctamente");
        } else {
            msg = new FacesMessage("Error Al Guardar");
        }
        empleados = new Empleados();
        usuarios = new Usuarios();
        FacesContext.getCurrentInstance().addMessage(null, msg);
        mostrarEmpleados();
        mostrarUsuarios();
    }

    public void mostrarEmpleados() {
        empleadosDao = new EmpleadosDao();
        listaEmpleados = empleadosDao.selectAllEmpleados();
    }

    public void mostrarUsuarios() {
        usuariosDao = new UsuariosDao();
        listaUsuarios = usuariosDao.selectAllUsuarios();
    }

    public void selectByIdEmpleado(Empleados id) {
        empleados = empleadosDao.selectByIdEmpleado(id);
        idMateriaVista = empleados.getIdMateria().getIdMateria();
    }

    public void selectByIdUsuario(Usuarios id) {
        usuarios = usuariosDao.selectBtIdUsuarios(id);
        idRolVista = usuarios.getIdRol().getIdRol();
    }

    public void actualizarEmpleado() {

        Materias idMaterias = new Materias();
        idMaterias.setIdMateria(idMateriaVista);
        empleados.setIdMateria(idMaterias);

        String mensaje = genericDao.modificarEntidad(empleados);
        msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        mostrarEmpleados();

    }

    public void eliminarEmpleado(Empleados id) {
        String mensaje = empleadosDao.deleteEmpleado(id);
        msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void llenarSelectRoles() {
        listaRoles = rolesDao.selectAllRoles();
        for (Roles r : listaRoles) {
            seleccionarRol.put(r.getRol(), String.valueOf(r.getIdRol()));
        }
    }

//    public void llenarSelectMaterias() {
//        listaMaterias = materiasDao.allMaterias();
//        for (Materias m : listaMaterias) {
//            seleccionarMateria.put(m.getMateria(), String.valueOf(m.getIdMateria()));
//        }
//    }
    public void llenarSelectEstados() {
        listaEstados = estadosDao.selectAllEstados();
        for (Estados e : listaEstados) {
            seleccionarEstados.put(e.getEstado(), String.valueOf(e.getIdEstado()));
        }
    }

    public void llenarSelectEspecialidades() {
        listaOpccionesEsp = opcionDao.allOpcionEspe();
        for (OpcionEspe oe : listaOpccionesEsp) {
            seleccionarOpccionesEsp.put(oe.getDescripcion(), String.valueOf(oe.getIdOpcionEspe()));
        }
    }

    public void llenarSelectMateriasByOpcionEspecialidad() {
        System.out.println("esssssiddddd::::" + idOpccionesEspVista);
        if (idOpccionesEspVista > 0) {
            System.out.println("esssssiddddd::::" + idOpccionesEspVista);
            listaMaterias = materiasDao.seleccionarMateriasPorOpcEspe(idOpccionesEspVista);
            seleccionarMateria = new HashMap<String, String>();
            for (Materias m : listaMaterias) {
                seleccionarMateria.put(m.getMateria(), String.valueOf(m.getIdMateria()));
            }
        } else {
            listaMaterias = materiasDao.allMaterias();
            for (Materias m : listaMaterias) {
                seleccionarMateria.put(m.getMateria(), String.valueOf(m.getIdMateria()));
            }
        }
    }

    public void actualizarUsuarios() {
        
        UtilVarios uv = new UtilVarios();
        usuarios.setPass(uv.convertToMd5(usuarios.getPass()));

        Roles idRoles = new Roles();
        idRoles.setIdRol(idRolVista);
        usuarios.setIdRol(idRoles);

        Estados idEstados = new Estados();
        idEstados.setIdEstado(idEstadoVista);
        usuarios.setIdEstado(idEstados);

        String mensaje = genericDao.modificarEntidad(usuarios);
        msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        mostrarUsuarios();

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

    public List<Roles> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Roles> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<Estados> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estados> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<Materias> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(List<Materias> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<OpcionEspe> getListaOpccionesEsp() {
        return listaOpccionesEsp;
    }

    public void setListaOpccionesEsp(List<OpcionEspe> listaOpccionesEsp) {
        this.listaOpccionesEsp = listaOpccionesEsp;
    }

    public Map<String, String> getSeleccionarRol() {
        return seleccionarRol;
    }

    public void setSeleccionarRol(Map<String, String> seleccionarRol) {
        this.seleccionarRol = seleccionarRol;
    }

    public Map<String, String> getSeleccionarEstados() {
        return seleccionarEstados;
    }

    public void setSeleccionarEstados(Map<String, String> seleccionarEstados) {
        this.seleccionarEstados = seleccionarEstados;
    }

    public Map<String, String> getSeleccionarMateria() {
        return seleccionarMateria;
    }

    public void setSeleccionarMateria(Map<String, String> seleccionarMateria) {
        this.seleccionarMateria = seleccionarMateria;
    }

    public Map<String, String> getSeleccionarOpccionesEsp() {
        return seleccionarOpccionesEsp;
    }

    public void setSeleccionarOpccionesEsp(Map<String, String> seleccionarOpccionesEsp) {
        this.seleccionarOpccionesEsp = seleccionarOpccionesEsp;
    }

    public Map<String, String> getSelectMateriaByOpcionEsp() {
        return selectMateriaByOpcionEsp;
    }

    public void setSelectMateriaByOpcionEsp(Map<String, String> selectMateriaByOpcionEsp) {
        this.selectMateriaByOpcionEsp = selectMateriaByOpcionEsp;
    }

    public Integer getIdRolVista() {
        return idRolVista;
    }

    public void setIdRolVista(Integer idRolVista) {
        this.idRolVista = idRolVista;
    }

    public Integer getIdEstadoVista() {
        return idEstadoVista;
    }

    public void setIdEstadoVista(Integer idEstadoVista) {
        this.idEstadoVista = idEstadoVista;
    }

    public Integer getIdMateriaVista() {
        return idMateriaVista;
    }

    public void setIdMateriaVista(Integer idMateriaVista) {
        this.idMateriaVista = idMateriaVista;
    }

    public int getIdOpccionesEspVista() {
        return idOpccionesEspVista;
    }

    public void setIdOpccionesEspVista(int idOpccionesEspVista) {
        this.idOpccionesEspVista = idOpccionesEspVista;
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

    public RolesDao getRolesDao() {
        return rolesDao;
    }

    public void setRolesDao(RolesDao rolesDao) {
        this.rolesDao = rolesDao;
    }

    public MateriasDao getMateriasDao() {
        return materiasDao;
    }

    public void setMateriasDao(MateriasDao materiasDao) {
        this.materiasDao = materiasDao;
    }

    public UsuariosDao getUsuariosDao() {
        return usuariosDao;
    }

    public void setUsuariosDao(UsuariosDao usuariosDao) {
        this.usuariosDao = usuariosDao;
    }

    public EstadosDao getEstadosDao() {
        return estadosDao;
    }

    public void setEstadosDao(EstadosDao estadosDao) {
        this.estadosDao = estadosDao;
    }

    public OpcionDao getOpcionDao() {
        return opcionDao;
    }

    public void setOpcionDao(OpcionDao opcionDao) {
        this.opcionDao = opcionDao;
    }

}
