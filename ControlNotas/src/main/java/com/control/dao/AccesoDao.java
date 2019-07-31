/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entity.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel.rodriguezusam
 */
public class AccesoDao {

    EntityManagerFactory f = Persistence.createEntityManagerFactory("cnPU");
    EntityManager em = f.createEntityManager();

    private Usuarios usuario;
    private List<Usuarios> usuarioList;

    public Usuarios logeado(String user, String pass) {
        String sql = "SELECT u.id_usuario, u.usuario, u.pass, u.id_rol  FROM Usuarios u WHERE u.usuario ='"+user+"' AND u.pass ='"+ pass +"'";
        String mensaje = "";
        
        System.out.println("usuario en acces= " + user + " pass en acces= " + pass);
        
        try {
            usuario = (Usuarios) em.createNativeQuery(sql).getSingleResult();
        
            if (usuario != null) {
                mensaje = "Bienvenido";
            } else {
                mensaje = "Incorrecto";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
