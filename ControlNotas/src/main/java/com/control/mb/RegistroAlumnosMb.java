package com.control.mb;

import com.control.dao.AlumnosDao;
import com.control.dao.EncargadosDao;
import com.control.dao.GenericDao;
import com.control.entity.Alumnos;
import com.control.entity.Encargados;
import java.util.ArrayList;
import java.util.List;
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

    private Alumnos alumnos;
    private Encargados encargados;

    private List<Alumnos> listaAlumnos;
    private List<Encargados> listaEncargados;

    private GenericDao genericDao;
    private AlumnosDao alumnosDao;
    private EncargadosDao encargadosDao;

    @PostConstruct
    public void init() {
        alumnos = new Alumnos();
        encargados = new Encargados();

        listaAlumnos = new ArrayList<Alumnos>();
        listaEncargados = new ArrayList<Encargados>();

        genericDao = new GenericDao();
        alumnosDao = new AlumnosDao();
        encargadosDao = new EncargadosDao();

        mostrarAlumnos();
        mostrarEncargados();
    }

    public void mostrarAlumnos() {
        alumnosDao = new AlumnosDao();
        listaAlumnos = alumnosDao.allAlumnos();
    }

    public void mostrarEncargados() {
        encargadosDao = new EncargadosDao();
        listaEncargados = encargadosDao.selectAllEncargados();
    }

    public void selectByIdAlumno(Alumnos id) {
        alumnos = alumnosDao.selectByIdAlumno(id);
    }

    public void selectByIdEncargado(Alumnos id) {
        Encargados e = id.getIdEncargado();
        encargados = encargadosDao.selectByIdEncargado(e);
    }

    public void actualizarAlumno() {
        String mensaje = genericDao.modificarEntidad(alumnos);
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        mostrarAlumnos();
    }

    public void actualizarEncargado() {
        String mensaje = genericDao.modificarEntidad(encargados);
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        mostrarEncargados();
    }

    public Alumnos getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Alumnos alumnos) {
        this.alumnos = alumnos;
    }

    public Encargados getEncargados() {
        return encargados;
    }

    public void setEncargados(Encargados encargados) {
        this.encargados = encargados;
    }

    public List<Alumnos> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<Alumnos> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public List<Encargados> getListaEncargados() {
        return listaEncargados;
    }

    public void setListaEncargados(List<Encargados> listaEncargados) {
        this.listaEncargados = listaEncargados;
    }

    public GenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public AlumnosDao getAlumnosDao() {
        return alumnosDao;
    }

    public void setAlumnosDao(AlumnosDao alumnosDao) {
        this.alumnosDao = alumnosDao;
    }

    public EncargadosDao getEncargadosDao() {
        return encargadosDao;
    }

    public void setEncargadosDao(EncargadosDao encargadosDao) {
        this.encargadosDao = encargadosDao;
    }

}
