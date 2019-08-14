/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.util;

import com.control.dao.EvaluacionDao;
import com.control.dao.MatriculaDao;
import com.control.entity.Evaluacion;
import com.control.entity.Matricula;
import com.control.entity.OpcionEspe;
import com.control.entity.Opciones;
import com.control.entity.Reporte;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author manuel.rodriguezusam
 */
@ManagedBean
@ViewScoped
public class UtilReport {

    private JasperPrint jp;
    private Reporte reporte;
    private List<Reporte> reporteLista;

    private String nombre;

    private Integer matriculax;
    private Integer filtro;
    private Integer notax;

    private Integer porOpcion;
    private String porSeccion;

    private Integer porMateria;

    private boolean vistaxOpcion;
    private boolean vistaxSeccion;

    private boolean vistaXMatricula;
    private boolean vistaXNotas;
    private boolean vistaTodoMatricula;

    private boolean vistaXMateria;
    private boolean vistaTodoNotas;

    private List<Matricula> matriculaList;
    private Matricula matricula;

    private Evaluacion evaluacion;
    private List<Evaluacion> evaluacionList;
    private EvaluacionDao evaluacionDao;

    private MatriculaDao matriculaDao;

    @PostConstruct
    public void init() {
        matriculaDao = new MatriculaDao();
        evaluacionDao = new EvaluacionDao();
    }

    public void filtros() {
        if (filtro == 0) {
            System.out.println("NULO");

        } else if (filtro == 1) {
            vistaXMatricula = true;
            vistaXNotas = false;
            if (matriculax == null) {
                System.out.println("NULO en matriculax");
            } else if (matriculax == 1) {
                vistaTodoMatricula = true;
                vistaxOpcion = false;
                vistaxSeccion = false;

                vistaTodoNotas = false;
                vistaXMateria = false;

                System.out.println("Todo el reporte de matricula");
                try {
                    reporte = new Reporte();
                    reporteLista = new ArrayList<Reporte>();

                    matriculaList = new ArrayList<Matricula>();

                    matriculaList = matriculaDao.matriculaAll();
                    for (Matricula mat : matriculaList) {
                        reporte = new Reporte();
                        reporte.setMatricula(mat.getIdMatricula());
                        reporte.setaNombre(mat.getIdAlumno().getPrimerNombre() + " " + mat.getIdAlumno().getSegundoNombre());
                        reporte.setaApellido(mat.getIdAlumno().getPrimerApellido() + " " + mat.getIdAlumno().getSegundoApellido());
                        reporte.setOpcion(mat.getIdOpcion().getDescripcion());
                        reporte.setSeccion(mat.getIdOpcion().getSeccion());
                        reporteLista.add(reporte);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (matriculax == 2) {
                vistaTodoMatricula = false;
                vistaxOpcion = true;
                vistaxSeccion = false;

                vistaTodoNotas = false;
                vistaXMateria = false;
                System.out.println("Todo el reporte por Opcion");

                try {

                    reporteLista = new ArrayList<Reporte>();
                    matriculaList = new ArrayList<Matricula>();

                    if (porOpcion == null) {
                        System.out.println("nulo");
                    } else {
                        System.out.println("");
                        matriculaList = matriculaDao.porOpcion(porOpcion);

                        for (Matricula mat : matriculaList) {
                            reporte = new Reporte();

                            reporte.setMatricula(mat.getIdMatricula());
                            reporte.setaNombre(mat.getIdAlumno().getPrimerNombre() + " " + mat.getIdAlumno().getSegundoNombre());
                            reporte.setaApellido(mat.getIdAlumno().getPrimerApellido() + " " + mat.getIdAlumno().getSegundoApellido());
                            reporte.setOpcion(mat.getIdOpcion().getDescripcion());
                            reporte.setSeccion(mat.getIdOpcion().getSeccion());
                            reporteLista.add(reporte);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (matriculax == 3) {
                vistaTodoMatricula = false;
                vistaxOpcion = false;
                vistaxSeccion = true;

                vistaTodoNotas = false;
                vistaXMateria = false;
                try {
                    reporteLista = new ArrayList<Reporte>();
                    matriculaList = new ArrayList<Matricula>();
                    if (porSeccion == null) {
                        System.out.println("Nulo en Seccion");
                    } else {
                        System.out.println("Entro en seccion " + porSeccion);
                        matriculaList = matriculaDao.porSeccion(porSeccion);
                        for (Matricula mat : matriculaList) {
                            reporte = new Reporte();
                            reporte.setMatricula(mat.getIdMatricula());
                            reporte.setaNombre(mat.getIdAlumno().getPrimerNombre() + " " + mat.getIdAlumno().getSegundoNombre());
                            reporte.setaApellido(mat.getIdAlumno().getPrimerApellido() + " " + mat.getIdAlumno().getSegundoApellido());
                            reporte.setOpcion(mat.getIdOpcion().getDescripcion());
                            reporte.setSeccion(mat.getIdOpcion().getSeccion());
                            reporteLista.add(reporte);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("No se ha seleccionado nada");
            }

            //FILTRO POR NOTAS
        } else if (filtro == 2) {
            vistaXNotas = true;
            vistaXMatricula = false;
            if (notax == null) {
                System.out.println("Viene nulo");
            } else {
                if (notax == 1) {

                    vistaTodoMatricula = false;
                    vistaxOpcion = false;
                    vistaxSeccion = false;

                    vistaTodoNotas = true;
                    vistaXMateria = false;
                    System.out.println("Todo el reporte de Notas");
                    try {
                        System.out.println("2");
                        reporteLista = new ArrayList<Reporte>();
                        evaluacion = new Evaluacion();
                        evaluacionList = new ArrayList<Evaluacion>();
                        evaluacionList = evaluacionDao.evaluacionAll();

                        for (Evaluacion eva : evaluacionList) {
                            reporte = new Reporte();
                            reporte.setaNombre(eva.getIdAlumno().getPrimerNombre() + " " + eva.getIdAlumno().getSegundoNombre());
                            reporte.setaApellido(eva.getIdAlumno().getPrimerApellido() + " " + eva.getIdAlumno().getSegundoApellido());
                            reporte.setEspecialidad(eva.getIdProfesor().getIdMateria().getOpcionEspe().getDescripcion());
                            Matricula m = new Matricula();
                            m = matriculaDao.porAlumno(eva.getIdAlumno().getIdAlumno());
                            reporte.setOpcion(m.getIdOpcion().getDescripcion() + " " + m.getIdOpcion().getSeccion());
                            reporte.setMateria(eva.getIdProfesor().getIdMateria().getMateria());
                            reporte.setPro1(BigDecimal.valueOf(eva.getProEva1()));
                            reporte.setPro2(BigDecimal.valueOf(eva.getProEva2()));
                            reporte.setPro3(BigDecimal.valueOf(eva.getProEva3()));
                            reporte.setProf(BigDecimal.valueOf(eva.getProEvato()));
                            reporteLista.add(reporte);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (notax == 2) {
                    vistaTodoMatricula = false;
                    vistaxOpcion = false;
                    vistaxSeccion = false;

                    vistaTodoNotas = false;
                    vistaXMateria = true;
                    System.out.println("Todo el reporte de notas por Materia");
                    try {

                        reporteLista = new ArrayList<Reporte>();
                        evaluacion = new Evaluacion();
                        evaluacionList = new ArrayList<Evaluacion>();
                        if (porMateria == null) {
                            System.out.println("Viene nulo en materia");
                        } else {

                            evaluacionList = evaluacionDao.evaluacionXMateria(porMateria);
                            System.out.println("evaluacionXMateria " + evaluacionList.size());
                            for (Evaluacion eva : evaluacionList) {
                                System.out.println("no entra");
                                reporte = new Reporte();
                                reporte.setaNombre(eva.getIdAlumno().getPrimerNombre() + " " + eva.getIdAlumno().getSegundoNombre());
                                System.out.println("--" + reporte.getaNombre());
                                reporte.setaApellido(eva.getIdAlumno().getPrimerApellido() + " " + eva.getIdAlumno().getSegundoApellido());
                                reporte.setEspecialidad(eva.getIdProfesor().getIdMateria().getOpcionEspe().getDescripcion());
                                Matricula m = new Matricula();
                                m = matriculaDao.porAlumno(eva.getIdAlumno().getIdAlumno());
                                reporte.setOpcion(m.getIdOpcion().getDescripcion() + " " + m.getIdOpcion().getSeccion());
                                reporte.setMateria(eva.getIdProfesor().getIdMateria().getMateria());
                                reporte.setPro1(BigDecimal.valueOf(eva.getProEva1()));
                                reporte.setPro2(BigDecimal.valueOf(eva.getProEva2()));
                                reporte.setPro3(BigDecimal.valueOf(eva.getProEva3()));
                                reporte.setProf(BigDecimal.valueOf(eva.getProEvato()));
                                reporteLista.add(reporte);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("no entra perro");
                    }

                } else {
                    System.out.println("Viene nulo");
                }
            }
        } else {
            System.out.println("Viene nulo en filtro");
        }
    }

//    ------------------------------Todas las notas------------------------------------------------
//    ---------------------------------Notas por Materia---------------------------------------------
    public void reporteToPdf() throws IOException, JRException {
        filtros();
        System.out.println("---");
        JRBeanCollectionDataSource jdbc = new JRBeanCollectionDataSource(reporteLista);
        System.out.println("---");
        String reportPatch = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Notas.jasper");
        System.out.println("---");
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("---");
        jp = JasperFillManager.fillReport(reportPatch, map, jdbc);
        System.out.println("---");
        nombre = "matricula.pdf";
        System.out.println("---");
        toPdf();
        System.out.println("---");
    }

    public void toPdf() throws IOException, JRException {
        HttpServletResponse hsr = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        hsr.addHeader("Context-disposition", "attachment; fielanme= " + nombre);
        ServletOutputStream sos = hsr.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jp, sos);
        sos.flush();

        FacesContext.getCurrentInstance().responseComplete();

    }

    public JasperPrint getJp() {
        return jp;
    }

    public void setJp(JasperPrint jp) {
        this.jp = jp;
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public List<Reporte> getReporteLista() {
        return reporteLista;
    }

    public void setReporteLista(List<Reporte> reporteLista) {
        this.reporteLista = reporteLista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getMatriculax() {
        return matriculax;
    }

    public void setMatriculax(Integer matriculax) {
        this.matriculax = matriculax;
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public MatriculaDao getMatriculaDao() {
        return matriculaDao;
    }

    public void setMatriculaDao(MatriculaDao matriculaDao) {
        this.matriculaDao = matriculaDao;
    }

    public Integer getPorOpcion() {
        return porOpcion;
    }

    public void setPorOpcion(Integer porOpcion) {
        this.porOpcion = porOpcion;
    }

    public String getPorSeccion() {
        return porSeccion;
    }

    public void setPorSeccion(String porSeccion) {
        this.porSeccion = porSeccion;
    }

    public boolean isVistaxOpcion() {
        return vistaxOpcion;
    }

    public void setVistaxOpcion(boolean vistaxOpcion) {
        this.vistaxOpcion = vistaxOpcion;
    }

    public boolean isVistaxSeccion() {
        return vistaxSeccion;
    }

    public void setVistaxSeccion(boolean vistaxSeccion) {
        this.vistaxSeccion = vistaxSeccion;
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

    public EvaluacionDao getEvaluacionDao() {
        return evaluacionDao;
    }

    public void setEvaluacionDao(EvaluacionDao evaluacionDao) {
        this.evaluacionDao = evaluacionDao;
    }

    public Integer getPorMateria() {
        return porMateria;
    }

    public void setPorMateria(Integer porMateria) {
        this.porMateria = porMateria;
    }

    public Integer getFiltro() {
        return filtro;
    }

    public void setFiltro(Integer filtro) {
        this.filtro = filtro;
    }

    public Integer getNotax() {
        return notax;
    }

    public void setNotax(Integer notax) {
        this.notax = notax;
    }

    public boolean isVistaXMatricula() {
        return vistaXMatricula;
    }

    public void setVistaXMatricula(boolean vistaXMatricula) {
        this.vistaXMatricula = vistaXMatricula;
    }

    public boolean isVistaXNotas() {
        return vistaXNotas;
    }

    public void setVistaXNotas(boolean vistaXNotas) {
        this.vistaXNotas = vistaXNotas;
    }

    public boolean isVistaTodoMatricula() {
        return vistaTodoMatricula;
    }

    public void setVistaTodoMatricula(boolean vistaTodoMatricula) {
        this.vistaTodoMatricula = vistaTodoMatricula;
    }

    public boolean isVistaXMateria() {
        return vistaXMateria;
    }

    public void setVistaXMateria(boolean vistaXMateria) {
        this.vistaXMateria = vistaXMateria;
    }

    public boolean isVistaTodoNotas() {
        return vistaTodoNotas;
    }

    public void setVistaTodoNotas(boolean vistaTodoNotas) {
        this.vistaTodoNotas = vistaTodoNotas;
    }

}
