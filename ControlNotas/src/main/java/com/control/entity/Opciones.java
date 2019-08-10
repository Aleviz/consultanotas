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
 * @author alexander.emesticaus
 */
@Entity
@Table(name = "opciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opciones.findAll", query = "SELECT o FROM Opciones o")
    , @NamedQuery(name = "Opciones.findByIdOpcion", query = "SELECT o FROM Opciones o WHERE o.idOpcion = :idOpcion")
    , @NamedQuery(name = "Opciones.findBySeccion", query = "SELECT o FROM Opciones o WHERE o.seccion = :seccion")
    , @NamedQuery(name = "Opciones.findByAnio", query = "SELECT o FROM Opciones o WHERE o.anio = :anio")
    , @NamedQuery(name = "Opciones.findByDescripcion", query = "SELECT o FROM Opciones o WHERE o.descripcion = :descripcion")
   , @NamedQuery(name= "Opciones.findAllxOpcionEspe", query = "SELECT o FROM Opciones o WHERE o.idOpcionEspe.idOpcionEspe = :idOpcionEspe")})
public class Opciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_opcion")
    private Integer idOpcion;
    @Size(max = 3)
    @Column(name = "seccion")
    private String seccion;
    @Column(name = "anio")
    private Integer anio;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_opcion_espe", referencedColumnName = "id_opcion_espe")
    @ManyToOne(optional = false)
    private OpcionEspe idOpcionEspe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOpcion")
    private List<Matricula> matriculaList;

    public Opciones() {
    }

    public Opciones(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public OpcionEspe getIdOpcionEspe() {
        return idOpcionEspe;
    }

    public void setIdOpcionEspe(OpcionEspe idOpcionEspe) {
        this.idOpcionEspe = idOpcionEspe;
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
        hash += (idOpcion != null ? idOpcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opciones)) {
            return false;
        }
        Opciones other = (Opciones) object;
        if ((this.idOpcion == null && other.idOpcion != null) || (this.idOpcion != null && !this.idOpcion.equals(other.idOpcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entity.Opciones[ idOpcion=" + idOpcion + " ]";
    }
    
}
