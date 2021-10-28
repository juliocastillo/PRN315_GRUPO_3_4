/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.forms;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import proyecto.ejb.TipoFacadeLocal;
import proyecto.entidades.Tipo;

/**
 *
 * @author Dell
 */
@Named(value = "tipoForm")
@SessionScoped
public class TipoForm implements Serializable {

    @Inject
    private TipoFacadeLocal tipoFacade;
    private List<Tipo> tipoList;
    private Tipo tipo;
    private Tipo tipoNuevo;

    @PostConstruct
    public void init() {
        cargar();
    }
    public void cargar(){
        try {
            tipoList = tipoFacade.findAll();
        } catch (Exception e) {
        }
    }
    //Metodo usado para actualizar un registro de la tabla Tipo
    public void actualizar() {
        tipoFacade.edit(tipo);
    }
    //metodo recive la fila seleccionada
    public void leerTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    //metodo sirve para guardar un registro en la tabla Tipo
    public void guardar(){
        tipoFacade.create(tipoNuevo);
        tipoNuevo = null;
        tipoNuevo = new Tipo();
    }
    public void eliminar(){
        tipoFacade.remove(tipo);
        cargar();
    }

    public TipoForm() {
        this.tipoNuevo = new Tipo();
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

    public Tipo getTipoNuevo() {
        return tipoNuevo;
    }

    public void setTipoNuevo(Tipo tipoNuevo) {
        this.tipoNuevo = tipoNuevo;
    }

}
