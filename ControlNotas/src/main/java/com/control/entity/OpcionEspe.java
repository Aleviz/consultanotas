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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author manuel.rodriguezusam
 */
@Entity
@Table(name = "opcion_espe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpcionEspe.findAll", query = "SELECT o FROM OpcionEspe o")
    , @NamedQuery(name = "OpcionEspe.findByIdOpcionEspe", query = "SELECT o FROM OpcionEspe o WHERE o.idOpcionEspe = :idOpcionEspe")
    , @NamedQuery(name = "OpcionEspe.findByDescripcion", query = "SELECT o FROM OpcionEspe o WHERE o.descripcion = :descripcion")})
public class OpcionEspe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_opcion_espe")
    private Integer idOpcionEspe;
   
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id_especialidad")
    @ManyToOne(optional = false)
    private Especialidad idEspecialidad;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOpcionEspe")
    private List<Opciones> opcionesList;
   
    @OneToMany(mappedBy = "opcionEspe", cascade = CascadeType.PERSIST)
    private List<Materias> materiasList;

    public OpcionEspe() {
    }

    public OpcionEspe(Integer idOpcionEspe) {
        this.idOpcionEspe = idOpcionEspe;
    }

    public Integer getIdOpcionEspe() {
        return idOpcionEspe;
    }

    public void setIdOpcionEspe(Integer idOpcionEspe) {
        this.idOpcionEspe = idOpcionEspe;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Especialidad getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Especialidad idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    @XmlTransient
    public List<Opciones> getOpcionesList() {
        return opcionesList;
    }

    public void setOpcionesList(List<Opciones> opcionesList) {
        this.opcionesList = opcionesList;
    }

    @XmlTransient
    public List<Materias> getMateriasList() {
        return materiasList;
    }

    public void setMateriasList(List<Materias> materiasList) {
        this.materiasList = materiasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcionEspe != null ? idOpcionEspe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpcionEspe)) {
            return false;
        }
        OpcionEspe other = (OpcionEspe) object;
        if ((this.idOpcionEspe == null && other.idOpcionEspe != null) || (this.idOpcionEspe != null && !this.idOpcionEspe.equals(other.idOpcionEspe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entity.OpcionEspe[ idOpcionEspe=" + idOpcionEspe + " ]";
    }
    
}
