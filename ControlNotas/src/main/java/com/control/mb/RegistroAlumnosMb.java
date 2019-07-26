
package com.control.mb;

import com.control.dao.AlumnosDao;
import com.control.dao.EncargadosDao;
import com.control.dao.EvaluacionDao;
import com.control.dao.GenericDao;
import com.control.dao.UsuariosDao;
import com.control.entity.Alumnos;
import com.control.entity.Encargados;
import com.control.entity.Evaluacion;
import com.control.entity.Roles;
import com.control.entity.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
    private Roles roles;
    private Integer idRoles;
    
    @PostConstruct
    public void init(){
        alumnosList = new ArrayList<Alumnos>();
        alumnosDao = new AlumnosDao();
        usuarioDao = new UsuariosDao();
        encargadosDao = new EncargadosDao();
        evaluacionDao = new EvaluacionDao();
        genericDao = new GenericDao();
        usuario = new Usuarios();
        evaluacion = new Evaluacion();
        roles = new Roles();
        
    }
    
    public void guardarAlumno(){
        genericDao.insertarEntidad(encargado);
        roles.setIdRol(idRoles);
        usuario.setIdRol(roles);
        genericDao.insertarEntidad(usuario);       
    }
    
}
