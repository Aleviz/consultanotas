/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.mb;

import com.control.dao.AlumnosDao;
import com.control.dao.EvaluacionDao;
import com.control.dao.GenericDao;
import com.control.dao.MatriculaDao;
import com.control.dao.OpcionDao;
import com.control.entity.Alumnos;
import com.control.entity.Evaluacion;
import com.control.entity.Matricula;
import com.control.entity.OpcionEspe;
import com.control.entity.Opciones;
import com.control.entity.Usuarios;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author david.rodriguezusam
 */
@ManagedBean
@ViewScoped
public class RegistroNotasMb {

    private List<Alumnos> alumnosList;
    private List<Matricula> alumnosXGradoList;
    private List<Opciones> opcionList;
    private Opciones opcion;
    private Alumnos alumno;
    private OpcionEspe especialidad;
    private List<Opciones> opcionXEspecialidad;
    private List<OpcionEspe> especialidadList;
    private Evaluacion evaluacion;
    private List<Evaluacion> evaluacionList;
    private AlumnosDao alumnosDao;
    private OpcionDao opcionDao;
    private MatriculaDao matriculaDao;
    private GenericDao genericDao;
    private EvaluacionDao notasDao;
    private Map<String, String> selectEspecialidad;
    private Map<String, String> selectGrado;
    private Map<String, String> selectAlumno;
    private Integer idEspecialidad;
    private Integer idGrado;
    private Integer idAlumno;
    private boolean modificar1;
    private boolean modificar2;
    private boolean modificar3;
    @ManagedProperty(value = "#{LoginMB}")
    private LoginMB login;

    @PostConstruct
    public void init() {
        alumnosList = new ArrayList<Alumnos>();
        opcionList = new ArrayList<Opciones>();
        alumnosXGradoList = new ArrayList<Matricula>();
        opcionXEspecialidad = new ArrayList<Opciones>();
        opcion = new Opciones();
        matriculaDao = new MatriculaDao();
        especialidad = new OpcionEspe();
        especialidadList = new ArrayList<OpcionEspe>();
        alumno = new Alumnos();
        evaluacion = new Evaluacion();
        evaluacionList = new ArrayList<Evaluacion>();
        alumnosDao = new AlumnosDao();
        opcionDao = new OpcionDao();
        genericDao = new GenericDao();
        notasDao = new EvaluacionDao();
        idEspecialidad = 0;
        idAlumno = 0;
        idGrado = 0;
        selectAlumno = new HashMap<String, String>();
        selectEspecialidad = new HashMap<String, String>();
        selectGrado = new HashMap<String, String>();
        modificar1 = false;
        modificar2 = false;
        modificar3 = false;
        llenarSelectAlumno();
        llenarDatosPersonales();
        llenarSelectOpciones();
        llenarSelectGrado();
    }

    public void llenarDatosPersonales() {
        alumnosList = alumnosDao.allAlumnos();
    }

    public void llenarNotas() {
        evaluacionList = notasDao.evaluacionAll();
    }

    public void llenarSelectOpciones() {
        especialidadList = opcionDao.allOpcionEspe();
        System.out.println("-tamaño " + especialidadList.size());
        for (OpcionEspe o : especialidadList) {
            selectEspecialidad.put(o.getDescripcion(), String.valueOf(o.getIdOpcionEspe()));
        }
    }

    public void comparacion() {
        if (evaluacion.getProEva1() > 0) {
            modificar1 = true;
            System.out.println(modificar1 + " m1");
        }
        if (evaluacion.getProEva2() > 0) {
            modificar2 = true;
            System.out.println(modificar2 + "m2");
        }
        if (evaluacion.getProEva3() > 0) {
            modificar3 = true;
            System.out.println(modificar2 + "m2");
        }

    }

    public void modificarNotas() {

        double p1 = ((evaluacion.getEva1() + evaluacion.getEva2() + evaluacion.getEva3() + evaluacion.getEva4()) / 4);
        double p2 = ((evaluacion.getEva5() + evaluacion.getEva6() + evaluacion.getEva7() + evaluacion.getEva8()) / 4);
        double p3 = ((evaluacion.getEva9() + evaluacion.getEva10() + evaluacion.getEva11() + evaluacion.getEva12()) / 4);
        double pf = (p1 + p2 + p3) / 3;
        DecimalFormat formato = new DecimalFormat("##.##");
        evaluacion.setProEva1(Double.parseDouble(formato.format(p1)));
        evaluacion.setProEva2(Double.parseDouble(formato.format(p2)));
        evaluacion.setProEva3(Double.parseDouble(formato.format(p3)));
        
        evaluacion.setProEvato(Double.parseDouble(formato.format(pf)));
        genericDao.modificarEntidad(evaluacion);
    }

    public void llenarSelectGrado() {
        opcionList = opcionDao.allOpcion();
        System.out.println("-tamaño " + opcionList.size());
        for (Opciones o : opcionList) {
            selectGrado.put(o.getDescripcion() + " - " + o.getSeccion(), String.valueOf(o.getIdOpcion()));
        }
    }

    public void llenarSelectAlumno() {
        alumnosList = alumnosDao.allAlumnos();
        for (Alumnos a : alumnosList) {
            selectAlumno.put(a.getPrimerApellido() + " " + a.getSegundoApellido() + "-" + a.getCarnet(), String.valueOf(a.getIdAlumno()));
        }
    }

    public void llenarSelectOpcionxGrado() {
        opcionXEspecialidad = opcionDao.obtenerOpcionXEspecialidad(idEspecialidad);
        System.out.println("" + opcionXEspecialidad.size());
        selectGrado = new HashMap<String, String>();
        for (Opciones o : opcionXEspecialidad) {
            selectGrado.put(o.getDescripcion() + " - " + o.getSeccion(), String.valueOf(o.getIdOpcion()));
        }
    }

    public void llenarSelectAlumnoXGrado() {
        alumnosXGradoList = matriculaDao.AlumnosXGrado(idGrado);
        selectAlumno = new HashMap<String, String>();
        for (int i = 0; i < alumnosXGradoList.size(); i++) {
            alumno = new Alumnos();
            Matricula mat = alumnosXGradoList.get(i);
            System.out.println("primer apellido " + mat.getIdAlumno().getPrimerApellido());
            idAlumno = mat.getIdAlumno().getIdAlumno();
            String apellido = mat.getIdAlumno().getPrimerNombre() + " " + mat.getIdAlumno().getSegundoNombre() + " " + mat.getIdAlumno().getPrimerApellido() + " " + mat.getIdAlumno().getSegundoApellido()
                    + " - " + mat.getIdAlumno().getCarnet();
            selectAlumno.put(apellido, String.valueOf(alumno.getIdAlumno()));
        }
    }
//    -------------------------------------------------------------------------------

    public void llenarCamposAlumnos(int empleados) {
        System.out.println("---------------------" + idAlumno);
        alumno = alumnosDao.porAlumnos(idAlumno);
        System.out.println(alumno.getPrimerNombre());
        System.out.println("-----------empleado---------" + empleados);
        evaluacion = notasDao.porAlumnos(idAlumno, empleados);
        System.out.println(evaluacion.getIdAlumno().getPrimerNombre());
        System.out.println(evaluacion.getEva1());
    }

    public List<Alumnos> getAlumnosList() {
        return alumnosList;
    }

    public void setAlumnosList(List<Alumnos> alumnosList) {
        this.alumnosList = alumnosList;
    }

    public List<Opciones> getOpcionList() {
        return opcionList;
    }

    public void setOpcionList(List<Opciones> opcionList) {
        this.opcionList = opcionList;
    }

    public Opciones getOpcion() {
        return opcion;
    }

    public void setOpcion(Opciones opcion) {
        this.opcion = opcion;
    }

    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    public AlumnosDao getAlumnosDao() {
        return alumnosDao;
    }

    public void setAlumnosDao(AlumnosDao alumnosDao) {
        this.alumnosDao = alumnosDao;
    }

    public OpcionDao getOpcionDao() {
        return opcionDao;
    }

    public void setOpcionDao(OpcionDao opcionDao) {
        this.opcionDao = opcionDao;
    }

    public GenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public EvaluacionDao getNotasDao() {
        return notasDao;
    }

    public void setNotasDao(EvaluacionDao notasDao) {
        this.notasDao = notasDao;
    }

    public Map<String, String> getSelectEspecialidad() {
        return selectEspecialidad;
    }

    public void setSelectEspecialidad(Map<String, String> selectEspecialidad) {
        this.selectEspecialidad = selectEspecialidad;
    }

    public Map<String, String> getSelectGrado() {
        return selectGrado;
    }

    public void setSelectGrado(Map<String, String> selectGrado) {
        this.selectGrado = selectGrado;
    }

    public Map<String, String> getSelectAlumno() {
        return selectAlumno;
    }

    public void setSelectAlumno(Map<String, String> selectAlumno) {
        this.selectAlumno = selectAlumno;
    }

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public List<Matricula> getAlumnosXGradoList() {
        return alumnosXGradoList;
    }

    public void setAlumnosXGradoList(List<Matricula> alumnosXGradoList) {
        this.alumnosXGradoList = alumnosXGradoList;
    }

    public OpcionEspe getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(OpcionEspe especialidad) {
        this.especialidad = especialidad;
    }

    public List<Opciones> getOpcionXEspecialidad() {
        return opcionXEspecialidad;
    }

    public void setOpcionXEspecialidad(List<Opciones> opcionXEspecialidad) {
        this.opcionXEspecialidad = opcionXEspecialidad;
    }

    public List<OpcionEspe> getEspecialidadList() {
        return especialidadList;
    }

    public void setEspecialidadList(List<OpcionEspe> especialidadList) {
        this.especialidadList = especialidadList;
    }

    public MatriculaDao getMatriculaDao() {
        return matriculaDao;
    }

    public void setMatriculaDao(MatriculaDao matriculaDao) {
        this.matriculaDao = matriculaDao;
    }

    public boolean isModificar1() {
        return modificar1;
    }

    public void setModificar1(boolean modificar1) {
        this.modificar1 = modificar1;
    }

    public boolean isModificar2() {
        return modificar2;
    }

    public void setModificar2(boolean modificar2) {
        this.modificar2 = modificar2;
    }

    public boolean isModificar3() {
        return modificar3;
    }

    public void setModificar3(boolean modificar3) {
        this.modificar3 = modificar3;
    }

    public LoginMB getLogin() {
        return login;
    }

    public void setLogin(LoginMB login) {
        this.login = login;
    }

}
