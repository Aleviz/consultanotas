package com.control.util;

import java.io.Serializable;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author alexander.emesticaus
 */
public class UtilVarios implements Serializable{
    
    public String convertToMd5(String str) {
        String myPass = null;
        str = str.trim();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            myPass = DatatypeConverter.printHexBinary(digest).toLowerCase();
            System.out.println("pass: " + myPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPass;
    }
}