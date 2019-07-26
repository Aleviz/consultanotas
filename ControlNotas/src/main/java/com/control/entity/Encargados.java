/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author david.rodriguezusam
 */
@Entity
@Table(name = "encargados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encargados.findAll", query = "SELECT e FROM Encargados e")
    , @NamedQuery(name = "Encargados.findByIdEncargado", query = "SELECT e FROM Encargados e WHERE e.idEncargado = :idEncargado")
    , @NamedQuery(name = "Encargados.findByPrimerNombre", query = "SELECT e FROM Encargados e WHERE e.primerNombre = :primerNombre")
    , @NamedQuery(name = "Encargados.findBySegundoNombre", query = "SELECT e FROM Encargados e WHERE e.segundoNombre = :segundoNombre")
    , @NamedQuery(name = "Encargados.findByPrimerApellido", query = "SELECT e FROM Encargados e WHERE e.primerApellido = :primerApellido")
    , @NamedQuery(name = "Encargados.findBySegundoApellido", query = "SELECT e FROM Encargados e WHERE e.segundoApellido = :segundoApellido")
    , @NamedQuery(name = "Encargados.findByDui", query = "SELECT e FROM Encargados e WHERE e.dui = :dui")
    , @NamedQuery(name = "Encargados.findByNit", query = "SELECT e FROM Encargados e WHERE e.nit = :nit")
    , @NamedQuery(name = "Encargados.findByTelefono", query = "SELECT e FROM Encargados e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Encargados.findByDireccion", query = "SELECT e FROM Encargados e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Encargados.findByEdad", query = "SELECT e FROM Encargados e WHERE e.edad = :edad")})
public class Encargados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_encargado")
    private Integer idEncargado;
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
    @Size(max = 10)
    @Column(name = "dui")
    private String dui;
    @Size(max = 17)
    @Column(name = "nit")
    private String nit;
    @Size(max = 10)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 250)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "edad")
    private Integer edad;
    @OneToMany(mappedBy = "idEncargado")
    private List<Alumnos> alumnosList;

    public Encargados() {
    }

    public Encargados(Integer idEncargado) {
        this.idEncargado = idEncargado;
    }

    public Integer getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(Integer idEncargado) {
        this.idEncargado = idEncargado;
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

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @XmlTransient
    public List<Alumnos> getAlumnosList() {
        return alumnosList;
    }

    public void setAlumnosList(List<Alumnos> alumnosList) {
        this.alumnosList = alumnosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncargado != null ? idEncargado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encargados)) {
            return false;
        }
        Encargados other = (Encargados) object;
        if ((this.idEncargado == null && other.idEncargado != null) || (this.idEncargado != null && !this.idEncargado.equals(other.idEncargado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entity.Encargados[ idEncargado=" + idEncargado + " ]";
    }
    
}
