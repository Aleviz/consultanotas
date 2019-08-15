/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.mb;

import com.control.dao.EmpleadosDao;
import com.control.dao.GenericDao;
import com.control.dao.MateriasDao;
import com.control.dao.MatriculaDao;
import com.control.dao.OpcionDao;
import com.control.entity.Alumnos;
import com.control.entity.Empleados;
import com.control.entity.Encargados;
import com.control.entity.Estados;
import com.control.entity.Evaluacion;
import com.control.entity.Materias;
import com.control.entity.Matricula;
import com.control.entity.OpcionEspe;
import com.control.entity.Opciones;
import com.control.entity.Roles;
import com.control.entity.TipoMatricula;
import com.control.entity.Usuarios;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author manuel.rodriguezusam
 *
 */
@ManagedBean
@ViewScoped
public class RegistroMatriculaMb {

    //ENTITY
    private Matricula matricula;
    private Alumnos alumno;
    private TipoMatricula tipoMatricula;
    private Opciones opcion;
    private Usuarios usuario;
    private Roles rol;
    private Estados estado;
    private Encargados encargado;

    private Evaluacion evaluacion;
    private List<Evaluacion> listEva;

    private Materias materias;

    private Empleados profesor;

    private Map<String, String> opcionSelect;
    private Map<String, String> esSelect;

    //LIST
    private List<Matricula> matriculaList;
    private List<Materias> materiasList;
    private List<Empleados> profesorList;
    private List<Opciones> opcionesList;
    private List<OpcionEspe> opEsList;

    //INTEGER
    private Integer idAlumno;
    private Integer idTipo;
    private Integer idOpcion;
    private int opcionEspe;
    private Integer idRol;
    private Integer idUser;
    private Integer idEstado;
    private Integer idEncargado;

    private String valorRol;
    private String nombreOpcion;

    Object[] id;

    //DAOS
    private GenericDao gd;
    private MatriculaDao matriculaDao;
    private MateriasDao materiasDao;
    private EmpleadosDao empDao;
    private OpcionDao opDao;

    @PostConstruct
    public void init() {
        System.out.println("porque no me agarra ");
        //ENTITY
        matricula = new Matricula();
        alumno = new Alumnos();
        tipoMatricula = new TipoMatricula();
        opcion = new Opciones();
        materias = new Materias();
        profesor = new Empleados();
        usuario = new Usuarios();
        rol = new Roles();
        estado = new Estados();
        encargado = new Encargados();
        //LIST
        matriculaList = new ArrayList<Matricula>();
        materiasList = new ArrayList<Materias>();
        profesorList = new ArrayList<Empleados>();
        opcionesList = new ArrayList<Opciones>();
        idRol = 0;
        opcionSelect = new HashMap<String, String>();
        esSelect = new HashMap<String, String>();

        //DAOS
        gd = new GenericDao();
        matriculaDao = new MatriculaDao();
        materiasDao = new MateriasDao();
        empDao = new EmpleadosDao();
        opDao = new OpcionDao();
        //METODOS
        allMatricula();
//        porAlumno();
//        porOpcion();
//        porTipo();
        obtenerOpcionEspecialidad();
        obtenerOpcionEspe();
    }

    public void guardar() {

        //REGISTRO DE USUARIO
        matriculaDao = new MatriculaDao();
        gd = new GenericDao();
        usuario.setIdRol(rol);
        usuario.setUsuario(alumno.getCarnet());
        usuario.setIdEstado(estado);

        //REGISTRO DE ALUMNO
        alumno.setIdEncargado(encargado);
        alumno.setIdUsuario(usuario);
        //      -----------------------------------------
        //     REGISTRO DE MATRICULA       

        matricula.setIdAlumno(alumno);
        matricula.setIdTipo(tipoMatricula);
        matricula.setIdOpcion(opcion);
        Matricula mat = new Matricula();

        nombreOpcion = opcion.getDescripcion() + " " + opcion.getSeccion();
        System.out.println("nombre de opcion " + opcion.getDescripcion());
        System.out.println("DATOS DE ALUMNOS: ");
        System.out.println("ID ALUMNO : " + alumno.getIdAlumno());
        System.out.println("primer nombre: " + alumno.getPrimerNombre());
        System.out.println("primer nombre: " + alumno.getSegundoNombre());
        System.out.println("primer nombre: " + alumno.getPrimerApellido());
        System.out.println("primer nombre: " + alumno.getSegundoApellido());
        System.out.println("ID DEL USUARIO: " + alumno.getIdUsuario().getIdUsuario());
        System.out.println("ID DE ENCARGADO: " + alumno.getIdEncargado().getIdEncargado());

        System.out.println("DATOS DE ENCARGADOS: ");
        System.out.println("ID DE ENCARGADO " + encargado.getIdEncargado());
        System.out.println("primer nombre " + encargado.getPrimerNombre());
        System.out.println("segund nombre " + encargado.getSegundoNombre());
        System.out.println("primer apellido " + encargado.getPrimerApellido());
        System.out.println("segundo apellido " + encargado.getSegundoApellido());

        System.out.println("DATOS DE USUARIO :");
        System.out.println("usuario " + usuario.getUsuario());
        System.out.println("pass " + usuario.getPass());
        System.out.println("rol " + usuario.getIdRol().getIdRol());
        System.out.println("estado " + usuario.getIdEstado().getIdEstado());

        System.out.println("DATOS DE MATRICULA: ");
        System.out.println("ALUMNO :" + matricula.getIdAlumno().getIdAlumno());
        System.out.println("TIPO MATRICULA " + matricula.getIdTipo().getNombre());
        System.out.println("OPCION " + matricula.getIdOpcion().getDescripcion());

        gd.insertarEntidad(encargado);
        gd.insertarEntidad(usuario);
        gd.insertarEntidad(alumno);

        gd.insertarEntidad(matricula);
        profesorList = empDao.profXMateria(opcionEspe);

        int x = profesorList.size();
        System.out.println("x " + x);
        for (int i = 0; i < x; i++) {
            evaluacion = new Evaluacion();
            System.out.println("ENTRO AL FOR");
            Empleados profe = profesorList.get(i);
            int idpr = profe.getIdEmpleado();

            System.out.println("idpr " + idpr);
            evaluacion.setIdAlumno(alumno);
            evaluacion.setIdProfesor(profe);
            gd.insertarEntidad(evaluacion);
        }

// ----------------------------------------------------------------------
//      
    }

    public void obtenerOpciones(Integer idOpcion) {
        opcion.setIdOpcion(idOpcion);

    }

    public void allMatricula() {
        System.out.println("holaaaaaaaaaaaaa");

        matriculaList = matriculaDao.matriculaAll();
        System.out.println("matricula lista " + matriculaList.size());
    }

    public void porAlumno() {
        matricula = new Matricula();
        matricula = matriculaDao.porAlumno(idAlumno);
    }

    public void porOpcion() {
        matricula = new Matricula();
//        matricula = matriculaDao.porOpcion(idOpcion);
    }

    public void porTipo() {
        matricula = new Matricula();
        matricula = matriculaDao.porTipo(idTipo);
    }

    public void obtenerOpcionEspecialidad() {
        opEsList = opDao.allOpcionEspe();
        for (OpcionEspe op : opEsList) {
            esSelect.put(op.getDescripcion(), String.valueOf(op.getIdOpcionEspe()));
        }
    }

    public void obtenerOpcionEspe() {
        OpcionEspe opEs = new OpcionEspe();
        opEs.setIdOpcionEspe(opcionEspe);
        System.out.println("Valor del opcion especialdiad " + opcionEspe);
        opcionesList = opDao.obtenerOpcionesXEspe(opcionEspe);
        opcionSelect = new HashMap<String, String>();
        for (Opciones o : opcionesList) {
            opcionSelect.put(o.getDescripcion(), String.valueOf(o.getIdOpcion()));
        }
        System.out.println("OpcionList " + opcionesList.size());
        System.out.println("OpcionSelect " + opcionSelect.size());
    }

    //GETTER Y SETTER
    public Integer getIdAlumno() {
        if (idAlumno == null) {

            System.out.println("NULOOOO IDALUMNO");
        } else {
            System.out.println("No viene nulo");
        }
        return idAlumno;

    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdTipo() {

        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Alumnos getAlumno() {

        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public TipoMatricula getTipoMatricula() {
        return tipoMatricula;
    }

    public void setTipoMatricula(TipoMatricula tipoMatricula) {
        this.tipoMatricula = tipoMatricula;
    }

    public Opciones getOpcion() {
        return opcion;
    }

    public void setOpcion(Opciones opcion) {
        this.opcion = opcion;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Encargados getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargados encargado) {
        this.encargado = encargado;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(Integer idEncargado) {
        this.idEncargado = idEncargado;
    }

    public Object[] getId() {
        return id;
    }

    public void setId(Object[] id) {
        this.id = id;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public List<Evaluacion> getListEva() {
        return listEva;
    }

    public void setListEva(List<Evaluacion> listEva) {
        this.listEva = listEva;
    }

    public Materias getMaterias() {
        return materias;
    }

    public void setMaterias(Materias materias) {
        this.materias = materias;
    }

    public Empleados getProfesor() {
        return profesor;
    }

    public void setProfesor(Empleados profesor) {
        this.profesor = profesor;
    }

    public List<Materias> getMateriasList() {
        return materiasList;
    }

    public void setMateriasList(List<Materias> materiasList) {
        this.materiasList = materiasList;
    }

    public List<Empleados> getProfesorList() {
        return profesorList;
    }

    public void setProfesorList(List<Empleados> profesorList) {
        this.profesorList = profesorList;
    }

    public Integer getOpcionEspe() {
        return opcionEspe;
    }

    public void setOpcionEspe(Integer opcionEspe) {
        this.opcionEspe = opcionEspe;
    }

    public MateriasDao getMateriasDao() {
        return materiasDao;
    }

    public void setMateriasDao(MateriasDao materiasDao) {
        this.materiasDao = materiasDao;
    }

    public EmpleadosDao getEmpDao() {
        return empDao;
    }

    public void setEmpDao(EmpleadosDao empDao) {
        this.empDao = empDao;
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public GenericDao getGd() {
        return gd;
    }

    public void setGd(GenericDao gd) {
        this.gd = gd;
    }

    public MatriculaDao getMatriculaDao() {
        return matriculaDao;
    }

    public void setMatriculaDao(MatriculaDao matriculaDao) {
        this.matriculaDao = matriculaDao;
    }

    public String getValorRol() {
        return valorRol;
    }

    public void setValorRol(String valorRol) {
        this.valorRol = valorRol;
    }

    public Map<String, String> getOpcionSelect() {
        return opcionSelect;
    }

    public void setOpcionSelect(Map<String, String> opcionSelect) {
        this.opcionSelect = opcionSelect;
    }

    public List<Opciones> getOpcionesList() {
        return opcionesList;
    }

    public void setOpcionesList(List<Opciones> opcionesList) {
        this.opcionesList = opcionesList;
    }

    public OpcionDao getOpDao() {
        return opDao;
    }

    public void setOpDao(OpcionDao opDao) {
        this.opDao = opDao;
    }

    public Map<String, String> getEsSelect() {
        return esSelect;
    }

    public void setEsSelect(Map<String, String> esSelect) {
        this.esSelect = esSelect;
    }

    public List<OpcionEspe> getOpEsList() {
        return opEsList;
    }

    public void setOpEsList(List<OpcionEspe> opEsList) {
        this.opEsList = opEsList;
    }

    public String getNombreOpcion() {
        return nombreOpcion;
    }

    public void setNombreOpcion(String nombreOpcion) {
        this.nombreOpcion = nombreOpcion;
    }

}
