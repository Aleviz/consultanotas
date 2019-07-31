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
 * @author alexander.emesticaus
 */
@Entity
@Table(name = "especialidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Especialidades.findAll", query = "SELECT e FROM Especialidades e")
    , @NamedQuery(name = "Especialidades.findByIdEspecialidad", query = "SELECT e FROM Especialidades e WHERE e.idEspecialidad = :idEspecialidad")
    , @NamedQuery(name = "Especialidades.findByDescripccion", query = "SELECT e FROM Especialidades e WHERE e.descripccion = :descripccion")
    , @NamedQuery(name = "Especialidades.findByEspecialidad", query = "SELECT e FROM Especialidades e WHERE e.especialidad = :especialidad")})
public class Especialidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_especialidad")
    private Integer idEspecialidad;
    @Size(max = 255)
    @Column(name = "descripccion")
    private String descripccion;
    @Size(max = 255)
    @Column(name = "especialidad")
    private String especialidad;
    @OneToMany(mappedBy = "idEspecialidad")
    private List<Bachillerato> bachilleratoList;
    @OneToMany(mappedBy = "idEspecialidad")
    private List<MateriaEspecialidad> materiaEspecialidadList;

    public Especialidades() {
    }

    public Especialidades(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getDescripccion() {
        return descripccion;
    }

    public void setDescripccion(String descripccion) {
        this.descripccion = descripccion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @XmlTransient
    public List<Bachillerato> getBachilleratoList() {
        return bachilleratoList;
    }

    public void setBachilleratoList(List<Bachillerato> bachilleratoList) {
        this.bachilleratoList = bachilleratoList;
    }

    @XmlTransient
    public List<MateriaEspecialidad> getMateriaEspecialidadList() {
        return materiaEspecialidadList;
    }

    public void setMateriaEspecialidadList(List<MateriaEspecialidad> materiaEspecialidadList) {
        this.materiaEspecialidadList = materiaEspecialidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspecialidad != null ? idEspecialidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especialidades)) {
            return false;
        }
        Especialidades other = (Especialidades) object;
        if ((this.idEspecialidad == null && other.idEspecialidad != null) || (this.idEspecialidad != null && !this.idEspecialidad.equals(other.idEspecialidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entity.Especialidades[ idEspecialidad=" + idEspecialidad + " ]";
    }
    
}
