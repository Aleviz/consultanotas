/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.control.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "evaluacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT e FROM Evaluacion e")
    , @NamedQuery(name = "Evaluacion.findByIdEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.idEvaluacion = :idEvaluacion")
    , @NamedQuery(name = "Evaluacion.findByEva1", query = "SELECT e FROM Evaluacion e WHERE e.eva1 = :eva1")
    , @NamedQuery(name = "Evaluacion.findByEva2", query = "SELECT e FROM Evaluacion e WHERE e.eva2 = :eva2")
    , @NamedQuery(name = "Evaluacion.findByEva3", query = "SELECT e FROM Evaluacion e WHERE e.eva3 = :eva3")
    , @NamedQuery(name = "Evaluacion.findByEva4", query = "SELECT e FROM Evaluacion e WHERE e.eva4 = :eva4")
    , @NamedQuery(name = "Evaluacion.findByProEva1", query = "SELECT e FROM Evaluacion e WHERE e.proEva1 = :proEva1")
    , @NamedQuery(name = "Evaluacion.findByEva5", query = "SELECT e FROM Evaluacion e WHERE e.eva5 = :eva5")
    , @NamedQuery(name = "Evaluacion.findByEva6", query = "SELECT e FROM Evaluacion e WHERE e.eva6 = :eva6")
    , @NamedQuery(name = "Evaluacion.findByEva7", query = "SELECT e FROM Evaluacion e WHERE e.eva7 = :eva7")
    , @NamedQuery(name = "Evaluacion.findByEva8", query = "SELECT e FROM Evaluacion e WHERE e.eva8 = :eva8")
    , @NamedQuery(name = "Evaluacion.findByProEva2", query = "SELECT e FROM Evaluacion e WHERE e.proEva2 = :proEva2")
    , @NamedQuery(name = "Evaluacion.findByEva9", query = "SELECT e FROM Evaluacion e WHERE e.eva9 = :eva9")
    , @NamedQuery(name = "Evaluacion.findByEva10", query = "SELECT e FROM Evaluacion e WHERE e.eva10 = :eva10")
    , @NamedQuery(name = "Evaluacion.findByEva11", query = "SELECT e FROM Evaluacion e WHERE e.eva11 = :eva11")
    , @NamedQuery(name = "Evaluacion.findByEva12", query = "SELECT e FROM Evaluacion e WHERE e.eva12 = :eva12")
    , @NamedQuery(name = "Evaluacion.findByProEva3", query = "SELECT e FROM Evaluacion e WHERE e.proEva3 = :proEva3")
    , @NamedQuery(name = "Evaluacion.findByProEvato", query = "SELECT e FROM Evaluacion e WHERE e.proEvato = :proEvato")})
public class Evaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_evaluacion")
    private Integer idEvaluacion;
// @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "eva1")
    private Double eva1;
    @Column(name = "eva2")
    private Double eva2;
    @Column(name = "eva3")
    private Double eva3;
    @Column(name = "eva4")
    private Double eva4;
    @Column(name = "proEva1")
    private Double proEva1;
    @Column(name = "eva5")
    private Double eva5;
    @Column(name = "eva6")
    private Double eva6;
    @Column(name = "eva7")
    private Double eva7;
    @Column(name = "eva8")
    private Double eva8;
    @Column(name = "proEva2")
    private Double proEva2;
    @Column(name = "eva9")
    private Double eva9;
    @Column(name = "eva10")
    private Double eva10;
    @Column(name = "eva11")
    private Double eva11;
    @Column(name = "eva12")
    private Double eva12;
    @Column(name = "proEva3")
    private Double proEva3;
    @Column(name = "proEvato")
    private Double proEvato;
    @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno")
    @ManyToOne(optional = false)
    private Alumnos idAlumno;
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleados idProfesor;

    public Evaluacion() {
    }

    public Evaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Double getEva1() {
        return eva1;
    }

    public void setEva1(Double eva1) {
        this.eva1 = eva1;
    }

    public Double getEva2() {
        return eva2;
    }

    public void setEva2(Double eva2) {
        this.eva2 = eva2;
    }

    public Double getEva3() {
        return eva3;
    }

    public void setEva3(Double eva3) {
        this.eva3 = eva3;
    }

    public Double getEva4() {
        return eva4;
    }

    public void setEva4(Double eva4) {
        this.eva4 = eva4;
    }

    public Double getProEva1() {
        return proEva1;
    }

    public void setProEva1(Double proEva1) {
        this.proEva1 = proEva1;
    }

    public Double getEva5() {
        return eva5;
    }

    public void setEva5(Double eva5) {
        this.eva5 = eva5;
    }

    public Double getEva6() {
        return eva6;
    }

    public void setEva6(Double eva6) {
        this.eva6 = eva6;
    }

    public Double getEva7() {
        return eva7;
    }

    public void setEva7(Double eva7) {
        this.eva7 = eva7;
    }

    public Double getEva8() {
        return eva8;
    }

    public void setEva8(Double eva8) {
        this.eva8 = eva8;
    }

    public Double getProEva2() {
        return proEva2;
    }

    public void setProEva2(Double proEva2) {
        this.proEva2 = proEva2;
    }

    public Double getEva9() {
        return eva9;
    }

    public void setEva9(Double eva9) {
        this.eva9 = eva9;
    }

    public Double getEva10() {
        return eva10;
    }

    public void setEva10(Double eva10) {
        this.eva10 = eva10;
    }

    public Double getEva11() {
        return eva11;
    }

    public void setEva11(Double eva11) {
        this.eva11 = eva11;
    }

    public Double getEva12() {
        return eva12;
    }

    public void setEva12(Double eva12) {
        this.eva12 = eva12;
    }

    public Double getProEva3() {
        return proEva3;
    }

    public void setProEva3(Double proEva3) {
        this.proEva3 = proEva3;
    }

    public Double getProEvato() {
        return proEvato;
    }

    public void setProEvato(Double proEvato) {
        this.proEvato = proEvato;
    }

    public Alumnos getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumnos idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Empleados getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Empleados idProfesor) {
        this.idProfesor = idProfesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvaluacion != null ? idEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
// TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.idEvaluacion == null && other.idEvaluacion != null) || (this.idEvaluacion != null && !this.idEvaluacion.equals(other.idEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.control.entity.Evaluacion[ idEvaluacion=" + idEvaluacion + " ]";
    }

}
