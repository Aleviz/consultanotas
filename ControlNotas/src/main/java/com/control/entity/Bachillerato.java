/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author david.rodriguezusam
 */
@Entity
@Table(name = "bachillerato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bachillerato.findAll", query = "SELECT b FROM Bachillerato b")
    , @NamedQuery(name = "Bachillerato.findByIdBachillerato", query = "SELECT b FROM Bachillerato b WHERE b.idBachillerato = :idBachillerato")
    , @NamedQuery(name = "Bachillerato.findBySeccionBachillerato", query = "SELECT b FROM Bachillerato b WHERE b.seccionBachillerato = :seccionBachillerato")})
public class Bachillerato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bachillerato")
    private Integer idBachillerato;
    @Column(name = "seccion_bachillerato")
    private Character seccionBachillerato;
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id_especialidad")
    @ManyToOne(optional = false)
    private Especialidades idEspecialidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBachillerato")
    private List<Matricula> matriculaList;

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

    public Character getSeccionBachillerato() {
        return seccionBachillerato;
    }

    public void setSeccionBachillerato(Character seccionBachillerato) {
        this.seccionBachillerato = seccionBachillerato;
    }

    public Especialidades getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Especialidades idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
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
