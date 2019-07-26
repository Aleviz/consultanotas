
package com.control.mb;

import com.control.dao.AlumnosDao;
import com.control.dao.EncargadosDao;
import com.control.dao.EvaluacionDao;
import com.control.dao.GenericDao;
import com.control.dao.RolesDao;
import com.control.dao.UsuariosDao;
import com.control.entity.Alumnos;
import com.control.entity.Encargados;
import com.control.entity.Evaluacion;
import com.control.entity.Roles;
import com.control.entity.Usuarios;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author david.rodriguezusam
 */
@ManagedBean
@ViewScoped
public class RegistroAlumnosMb {
    private List<Alumnos> alumnosList;
    private Alumnos alumno;
    private Encargados encargado;
    private AlumnosDao alumnosDao;
    private Evaluacion evaluacion;
    private UsuariosDao usuarioDao;
    private EncargadosDao encargadosDao;
    private EvaluacionDao evaluacionDao;
    private GenericDao genericDao;
    private Usuarios usuario;
    private RolesDao rolesDao;
    private List<Roles> rolesList;
    private Roles roles;
    private Map<String, String> selectRoles;
    private Integer idRoles;
    
    @PostConstruct
    public void init(){
        alumnosList = new ArrayList<Alumnos>();
        alumnosDao = new AlumnosDao();
        rolesDao = new RolesDao();
        rolesList = new ArrayList<Roles>();
        usuarioDao = new UsuariosDao();
        selectRoles = new HashMap<String, String>();
        encargadosDao = new EncargadosDao();
        evaluacionDao = new EvaluacionDao();
        genericDao = new GenericDao();
        usuario = new Usuarios();
        evaluacion = new Evaluacion();
        roles = new Roles(); 
        llenarSelectRoles();
    }
    
    public void guardarAlumno(){
        genericDao.insertarEntidad(encargado);
        roles.setIdRol(idRoles);
        usuario.setIdRol(roles);
        genericDao.insertarEntidad(usuario);
        FacesMessage msg = new FacesMessage("Guardardo con Exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void llenarSelectRoles(){
        rolesList = rolesDao.selectAllRoles();
        System.out.println("lista"+rolesList.size());
        for(Roles ro: rolesList){
            selectRoles.put(ro.getRol(), String.valueOf(ro.getIdRol()));
        }
    }
    
    public void guardarRoles(){
        genericDao.insertarEntidad(roles);
    }

    public List<Alumnos> getAlumnosList() {
        return alumnosList;
    }

    public void setAlumnosList(List<Alumnos> alumnosList) {
        this.alumnosList = alumnosList;
    }

    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public Encargados getEncargado() {
        return encargado;
    }

    public RolesDao getRolesDao() {
        return rolesDao;
    }

    public void setRolesDao(RolesDao rolesDao) {
        this.rolesDao = rolesDao;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    public Map<String, String> getSelectRoles() {
        return selectRoles;
    }

    public void setSelectRoles(Map<String, String> selectRoles) {
        this.selectRoles = selectRoles;
    }
    
    

    public void setEncargado(Encargados encargado) {
        this.encargado = encargado;
    }

    public AlumnosDao getAlumnosDao() {
        return alumnosDao;
    }

    public void setAlumnosDao(AlumnosDao alumnosDao) {
        this.alumnosDao = alumnosDao;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public UsuariosDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuariosDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public EncargadosDao getEncargadosDao() {
        return encargadosDao;
    }

    public void setEncargadosDao(EncargadosDao encargadosDao) {
        this.encargadosDao = encargadosDao;
    }

    public EvaluacionDao getEvaluacionDao() {
        return evaluacionDao;
    }

    public void setEvaluacionDao(EvaluacionDao evaluacionDao) {
        this.evaluacionDao = evaluacionDao;
    }

    public GenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Integer getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(Integer idRoles) {
        this.idRoles = idRoles;
    }
    
    
    
}
