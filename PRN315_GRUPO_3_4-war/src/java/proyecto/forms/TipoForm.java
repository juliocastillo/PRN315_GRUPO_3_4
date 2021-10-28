/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.forms;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import proyecto.ejb.TipoFacadeLocal;
import proyecto.entidades.Tipo;

/**
 *
 * @author Dell
 */
@Named(value = "tipoForm")
@SessionScoped
public class TipoForm implements Serializable{

    @EJB
    private TipoFacadeLocal tipoFacade;
    private List<Tipo> tipoList;
    private Tipo tipo;

    @PostConstruct
    public void init() {
        try {
            tipoList = tipoFacade.findAll();
        } catch (Exception e) {
        }
    }
    public void actualizar(){
        tipoFacade.edit(tipo);
    }
    public void leerTipo(Tipo tipo){
        this.tipo = tipo;
        System.out.println(this.tipo.getNombre());
    }

    public TipoForm() {
    }

    public List<Tipo> getTipoList() {
        return tipoList;
    }

    public void setTipoList(List<Tipo> tipoList) {
        this.tipoList = tipoList;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
}
