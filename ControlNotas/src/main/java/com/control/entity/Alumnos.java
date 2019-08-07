/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author manuel.rodriguezusam
 */
@Entity
@Table(name = "alumnos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumnos.findAll", query = "SELECT a FROM Alumnos a")
    , @NamedQuery(name = "Alumnos.findByIdAlumno", query = "SELECT a FROM Alumnos a WHERE a.idAlumno = :idAlumno")
    , @NamedQuery(name = "Alumnos.findByPrimerNombre", query = "SELECT a FROM Alumnos a WHERE a.primerNombre = :primerNombre")
    , @NamedQuery(name = "Alumnos.findBySegundoNombre", query = "SELECT a FROM Alumnos a WHERE a.segundoNombre = :segundoNombre")
    , @NamedQuery(name = "Alumnos.findByPrimerApellido", query = "SELECT a FROM Alumnos a WHERE a.primerApellido = :primerApellido")
    , @NamedQuery(name = "Alumnos.findBySegundoApellido", query = "SELECT a FROM Alumnos a WHERE a.segundoApellido = :segundoApellido")
    , @NamedQuery(name = "Alumnos.findByFechaNacimiento", query = "SELECT a FROM Alumnos a WHERE a.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Alumnos.findByTelefono", query = "SELECT a FROM Alumnos a WHERE a.telefono = :telefono")
    , @NamedQuery(name = "Alumnos.findByDireccion", query = "SELECT a FROM Alumnos a WHERE a.direccion = :direccion")
    , @NamedQuery(name = "Alumnos.findByCarnet", query = "SELECT a FROM Alumnos a WHERE a.carnet = :carnet")})
public class Alumnos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alumno")
    private Integer idAlumno;
    
    @Size(max = 30)
    @Column(name = "primer_nombre")
    private String primerNombre;
    
    @Size(max = 30)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    
    @Size(max = 30)
    @Column(name = "primer_apellido")
    private String primerApellido;
    
    @Size(max = 30)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    
    @Size(max = 9)
    @Column(name = "telefono")
    private String telefono;
    
    @Size(max = 250)
    @Column(name = "direccion")
    private String direccion;
    
    @Size(max = 15)
    @Column(name = "carnet")
    private String carnet;
    
    @JoinColumn(name = "id_encargado", referencedColumnName = "id_encargado")
    @ManyToOne
    private Encargados idEncargado;
    
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuarios idUsuario;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "idAlumno")
    private List<Evaluacion> evaluacionList;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "idAlumno")
    private List<Matricula> matriculaList;

    public Alumnos() {
    }

    public Alumnos(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public Encargados getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(Encargados idEncargado) {
        this.idEncargado = idEncargado;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlumno != null ? idAlumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumnos)) {
            return false;
        }
        Alumnos other = (Alumnos) object;
        if ((this.idAlumno == null && other.idAlumno != null) || (this.idAlumno != null && !this.idAlumno.equals(other.idAlumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entity.Alumnos[ idAlumno=" + idAlumno + " ]";
    }
    
}
