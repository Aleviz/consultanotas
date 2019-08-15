package com.control.entity;

/**
 *
 * @author alexander.emesticaus
 */
public class InstitucionImagen {
    
    private Integer id_colegio;
    private String nombre;
    private String direccion;
    private String telefono;
    private String logo;
    private String correo;

    public InstitucionImagen() {
    }

    public Integer getId_colegio() {
        return id_colegio;
    }

    public void setId_colegio(Integer id_colegio) {
        this.id_colegio = id_colegio;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
