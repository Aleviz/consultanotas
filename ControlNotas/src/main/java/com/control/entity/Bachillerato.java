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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexander.emesticaus
 */
@Entity
@Table(name = "bachillerato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bachillerato.findAll", query = "SELECT b FROM Bachillerato b")
    , @NamedQuery(name = "Bachillerato.findByIdBachillerato", query = "SELECT b FROM Bachillerato b WHERE b.idBachillerato = :idBachillerato")
    , @NamedQuery(name = "Bachillerato.findByGrado", query = "SELECT b FROM Bachillerato b WHERE b.grado = :grado")
    , @NamedQuery(name = "Bachillerato.findBySeccionBachillerato", query = "SELECT b FROM Bachillerato b WHERE b.seccionBachillerato = :seccionBachillerato")})
public class Bachillerato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bachillerato")
    private Integer idBachillerato;
    @Column(name = "grado")
    private Integer grado;
    @Column(name = "seccion_bachillerato")
    private Character seccionBachillerato;
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id_especialidad")
    @ManyToOne
    private Especialidad idEspecialidad;

    public Bachillerato() {
    }

    public Bachillerato(Integer idBachillerato) {
        this.idBachillerato = idBachillerato;
    }

    public Integer getIdBachillerato() {
        return idBachillerato;
    }

    public void setIdBachillerato(Integer idBachillerato) {
        this.idBachillerato = idBachillerato;
    }

    public Integer getGrado() {
        return grado;
    }

    public void setGrado(Integer grado) {
        this.grado = grado;
    }

    public Character getSeccionBachillerato() {
        return seccionBachillerato;
    }

    public void setSeccionBachillerato(Character seccionBachillerato) {
        this.seccionBachillerato = seccionBachillerato;
    }

    public Especialidad getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Especialidad idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBachillerato != null ? idBachillerato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bachillerato)) {
            return false;
        }
        Bachillerato other = (Bachillerato) object;
        if ((this.idBachillerato == null && other.idBachillerato != null) || (this.idBachillerato != null && !this.idBachillerato.equals(other.idBachillerato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entity.Bachillerato[ idBachillerato=" + idBachillerato + " ]";
    }
    
}
