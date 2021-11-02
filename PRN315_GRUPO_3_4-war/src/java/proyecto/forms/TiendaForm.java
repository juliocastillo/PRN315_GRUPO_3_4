/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import proyecto.ejb.DireccionFacadeLocal;
import proyecto.ejb.TiendaFacadeLocal;
import proyecto.entidades.Direccion;
import proyecto.entidades.Tienda;

/**
 *
 * @author Dell
 */
@Named(value = "tiendaForm")
@SessionScoped
public class TiendaForm implements Serializable {

    @Inject
    private DireccionFacadeLocal direccionFacade;

    @Inject
    private TiendaFacadeLocal tiendaFacade;
    
    private List<Direccion> direccionList;
    
    private List<Tienda> tiendaList;
    
    private Direccion direccion;
    
    private Tienda tiendaNueva;
    private Tienda tienda;
    
    @PostConstruct
    public void init(){
        cargar();
    }
    public void cargar(){
        try{
            tiendaList = tiendaFacade.findAll();
            direccionList = direccionFacade.findAll();
        }catch(Exception e){
            
        }
    }
    public void guardar(){
        tiendaNueva.setTiendaId(BigDecimal.valueOf(155D));
        tiendaNueva.setDireccionId(direccion);
        tiendaFacade.create(tiendaNueva);
        cargar();
        limpiar();
    }
    public void leerRow(Tienda tienda){
        this.tienda = tienda;
    }
    public void update(){
        tienda.setDireccionId(direccion);
        tiendaFacade.edit(tienda);
        cargar();
        limpiar();
    }
    public void limpiar(){
        direccion = null;
        direccion = new Direccion();
        tiendaNueva = null;
        tiendaNueva = new Tienda();
        tienda = null;
        tienda = new Tienda();
    }

    public TiendaForm() {
        direccion = new Direccion();
        tiendaNueva = new Tienda();
        tienda = new Tienda();
    }

    public List<Direccion> getDireccionList() {
        return direccionList;
    }

    public void setDireccionList(List<Direccion> direccionList) {
        this.direccionList = direccionList;
    }

    public List<Tienda> getTiendaList() {
        return tiendaList;
    }

    public void setTiendaList(List<Tienda> tiendaList) {
        this.tiendaList = tiendaList;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Tienda getTiendaNueva() {
        return tiendaNueva;
    }

    public void setTiendaNueva(Tienda tiendaNueva) {
        this.tiendaNueva = tiendaNueva;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }
    
}
