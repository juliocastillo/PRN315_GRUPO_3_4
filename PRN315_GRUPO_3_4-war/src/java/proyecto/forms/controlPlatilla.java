/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Dell
 */
@Named(value = "controlPlatilla")
@ViewScoped
public class controlPlatilla implements Serializable {

    public void session() {
        try {
            String pass = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pass");
            if(pass==null){
                FacesContext.getCurrentInstance().getExternalContext().redirect("/PRN315_GRUPO_3_4-war/index.jsf");
            }
        } catch (Exception e) {

        }
    }
    public String cerrarSession(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/PRN315_GRUPO_3_4-war/index.jsf";
    }

    public controlPlatilla() {
    }

}
