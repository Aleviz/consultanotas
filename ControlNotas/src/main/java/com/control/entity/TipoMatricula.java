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
 * @author manuel.rodriguezusam
 */
@Entity
@Table(name = "tipo_matricula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMatricula.findAll", query = "SELECT t FROM TipoMatricula t")
    , @NamedQuery(name = "TipoMatricula.findByIdTipoMat", query = "SELECT t FROM TipoMatricula t WHERE t.idTipoMat = :idTipoMat")
    , @NamedQuery(name = "TipoMatricula.findByNombre", query = "SELECT t FROM TipoMatricula t WHERE t.nombre = :nombre")})
public class TipoMatricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_mat")
    private Integer idTipoMat;
    @Size(max = 10)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idTipo")
    private List<Matricula> matriculaList;

    public TipoMatricula() {
    }

    public TipoMatricula(Integer idTipoMat) {
        this.idTipoMat = idTipoMat;
    }

    public Integer getIdTipoMat() {
        return idTipoMat;
    }

    public void setIdTipoMat(Integer idTipoMat) {
        this.idTipoMat = idTipoMat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idTipoMat != null ? idTipoMat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMatricula)) {
            return false;
        }
        TipoMatricula other = (TipoMatricula) object;
        if ((this.idTipoMat == null && other.idTipoMat != null) || (this.idTipoMat != null && !this.idTipoMat.equals(other.idTipoMat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entity.TipoMatricula[ idTipoMat=" + idTipoMat + " ]";
    }
    
}
