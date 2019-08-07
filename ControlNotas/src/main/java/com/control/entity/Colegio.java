/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
<<<<<<< HEAD
 * @author david.rodriguezusam
=======
 * @author alexander.emesticaus
>>>>>>> Developer
 */
@Entity
@Table(name = "colegio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colegio.findAll", query = "SELECT c FROM Colegio c")
    , @NamedQuery(name = "Colegio.findByIdColegio", query = "SELECT c FROM Colegio c WHERE c.idColegio = :idColegio")
    , @NamedQuery(name = "Colegio.findByNombre", query = "SELECT c FROM Colegio c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Colegio.findByDireccion", query = "SELECT c FROM Colegio c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "Colegio.findByTelefono", query = "SELECT c FROM Colegio c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Colegio.findByCorreo", query = "SELECT c FROM Colegio c WHERE c.correo = :correo")})
public class Colegio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_colegio")
    private Integer idColegio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "telefono")
    private String telefono;
    @Lob
    @Column(name = "logo")
    private byte[] logo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "correo")
    private String correo;

    public Colegio() {
    }

    public Colegio(Integer idColegio) {
        this.idColegio = idColegio;
    }

    public Colegio(Integer idColegio, String nombre, String direccion, String telefono, String correo) {
        this.idColegio = idColegio;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Integer getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Integer idColegio) {
        this.idColegio = idColegio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idColegio != null ? idColegio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colegio)) {
            return false;
        }
        Colegio other = (Colegio) object;
        if ((this.idColegio == null && other.idColegio != null) || (this.idColegio != null && !this.idColegio.equals(other.idColegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entity.Colegio[ idColegio=" + idColegio + " ]";
    }
    
}
