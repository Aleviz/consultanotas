package com.control.mb;

import com.control.dao.ColegioDao;
import com.control.dao.GenericDao;
import com.control.entity.Colegio;
import com.control.entity.InstitucionImagen;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author alexander.emesticaus
 */
@ManagedBean
@ViewScoped
public class InstitucionMb implements Serializable {

    private Colegio colegio;

    private List<Colegio> listaColegio;
    private List<InstitucionImagen> listaIntitucion;

    private GenericDao gd;
    private ColegioDao colegioDao;

    private UploadedFile file;
    private String encodedString;
    private String imagen;

    @PostConstruct
    public void init() {
        colegio = new Colegio();

        listaColegio = new ArrayList<Colegio>();
        listaIntitucion = new ArrayList<InstitucionImagen>();

        gd = new GenericDao();
        colegioDao = new ColegioDao();

        selectAllColegio();
    }

    public void guardarInstitucion() {
        FacesMessage mes;
        String msg = "";
        colegio.setLogo(base64());
        colegio = (Colegio) gd.insertarEntidad(colegio);
        if (null != colegio) {
            msg = "Colegio Guardado";
            mes = new FacesMessage(msg);
        } else {
            msg = "Error al Momento de Guardar";
            mes = new FacesMessage(msg);
        }
        FacesContext.getCurrentInstance().addMessage(null, mes);
        selectAllColegio();
    }

    public byte[] base64() {
        try {
            System.out.println("essssFileeee::::"+file);
            if (null != file) {
                byte[] bytes;
                bytes = file.getContents();
                byte[] encoded = Base64.getEncoder().encode(bytes);
                encodedString = new String(encoded, StandardCharsets.US_ASCII);
                System.out.println("esssssssssssss"+encodedString);
            } else {
                System.out.println("File nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Mira el error: " + e);
        }
        return encodedString.getBytes();
    }

    public void selectAllColegio() {
        listaIntitucion = new ArrayList<InstitucionImagen>();
        listaColegio = colegioDao.colegioAll();
        InstitucionImagen imagenInst;
        if (null != listaColegio) {
            for (Colegio c : listaColegio) {
                imagenInst = new InstitucionImagen();
                imagenInst.setId_colegio(c.getIdColegio());
                imagenInst.setNombre(c.getNombre());
                imagenInst.setDireccion(c.getDireccion());
                imagenInst.setTelefono(c.getTelefono());
                imagenInst.setLogo(new String(c.getLogo()));
                imagenInst.setCorreo(c.getCorreo());
                listaIntitucion.add(imagenInst);
            }
        }
    }

    public void selectByIdColegio(InstitucionImagen id) {
        colegio = colegioDao.colegioById(id);
        imagen = new String(colegio.getLogo());
    }
    
    public void actualizarColegio(){
        colegio.setLogo(base64());
        String mensaje = gd.modificarEntidad(colegio);
        colegio = new Colegio();
        FacesMessage msg =  new  FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        selectAllColegio();
    }

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }

    public List<Colegio> getListaColegio() {
        return listaColegio;
    }

    public void setListaColegio(List<Colegio> listaColegio) {
        this.listaColegio = listaColegio;
    }

    public List<InstitucionImagen> getListaIntitucion() {
        return listaIntitucion;
    }

    public void setListaIntitucion(List<InstitucionImagen> listaIntitucion) {
        this.listaIntitucion = listaIntitucion;
    }

    public GenericDao getGd() {
        return gd;
    }

    public void setGd(GenericDao gd) {
        this.gd = gd;
    }

    public ColegioDao getColegioDao() {
        return colegioDao;
    }

    public void setColegioDao(ColegioDao colegioDao) {
        this.colegioDao = colegioDao;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getEncodedString() {
        return encodedString;
    }

    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
