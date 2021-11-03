/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dell
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    private String usuario = "";
    private String pass = "";
    @PostConstruct
    public void init(){
        
    }

    public Login() {
    }
    public String iniciarSesion(){
        String redireccion = null;
        try{
            if(md5(usuario).equals("91f5167c34c400758115c2a6826ec2e3") && md5(pass).equals("c92f70ff68da8a499d765ebaa8251845")){
                redireccion ="PlantillaGrupo4.jsf?faces-redirect=true";
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pass", pass);
            }
        }catch(Exception e){
            
        }
        return redireccion;
    }

    public static String md5(String clear) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(clear.getBytes());
        int size = b.length;
        StringBuffer h = new StringBuffer(size);
        //algoritmo y arreglo md5
        for (int i = 0; i < size; i++) {
            int u = b[i] & 255;
            if (u < 16) {
                h.append("0" + Integer.toHexString(u));
            } else {
                h.append(Integer.toHexString(u));
            }
        }
        //clave encriptada
        return h.toString();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
