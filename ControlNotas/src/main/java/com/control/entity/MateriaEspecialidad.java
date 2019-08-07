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
 * @author david.rodriguezusam
 */
@Entity
@Table(name = "materia_especialidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaEspecialidad.findAll", query = "SELECT m FROM MateriaEspecialidad m")
    , @NamedQuery(name = "MateriaEspecialidad.findByIdMatEsp", query = "SELECT m FROM MateriaEspecialidad m WHERE m.idMatEsp = :idMatEsp")})
public class MateriaEspecialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mat_esp")
    private Integer idMatEsp;
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id_especialidad")
    @ManyToOne(optional = false)
    private Especialidades idEspecialidad;
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
    @ManyToOne(optional = false)
    private Materias idMateria;

    public MateriaEspecialidad() {
    }

    public MateriaEspecialidad(Integer idMatEsp) {
        this.idMatEsp = idMatEsp;
    }

    public Integer getIdMatEsp() {
        return idMatEsp;
    }

    public void setIdMatEsp(Integer idMatEsp) {
        this.idMatEsp = idMatEsp;
    }

    public Especialidades getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Especialidades idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Materias getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materias idMateria) {
        this.idMateria = idMateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatEsp != null ? idMatEsp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaEspecialidad)) {
            return false;
        }
        MateriaEspecialidad other = (MateriaEspecialidad) object;
        if ((this.idMatEsp == null && other.idMatEsp != null) || (this.idMatEsp != null && !this.idMatEsp.equals(other.idMatEsp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entity.MateriaEspecialidad[ idMatEsp=" + idMatEsp + " ]";
    }
    
}
