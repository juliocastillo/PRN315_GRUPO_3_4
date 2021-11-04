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
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import proyecto.ejb.CiudadFacadeLocal;
import proyecto.ejb.DireccionFacadeLocal;
import proyecto.ejb.PaisFacadeLocal;
import proyecto.entidades.Ciudad;
import proyecto.entidades.Direccion;
import proyecto.entidades.Pais;

/**
 *
 * @author Dell
 */
@Named(value = "direccionForm")
@SessionScoped
public class DireccionForm implements Serializable {

    @Inject
    private CiudadFacadeLocal ciudadFacade;

    @Inject
    private DireccionFacadeLocal direccionFacade;

    private List<Direccion> direccionList;
    private List<Ciudad> ciudadList;
    private Direccion direccionNuevo;
    private Direccion direccion;
    private Ciudad ciudad;

    @PostConstruct
    public void init() {
        direccionNuevo = new Direccion();
        ciudad = new Ciudad();
        cargar();
    }

    public DireccionForm() {
    }

    //Metodo utilizado para cargar las tablas
    public void cargar() {
        try {
            this.direccionList = direccionFacade.findAll();
            this.ciudadList = ciudadFacade.findAll();
        } catch (Exception e) {

        }
    }

    public void leerFila(Direccion direccion) {
        this.direccion = direccion;
    }

    public void guardar() {
        direccionNuevo.setDireccionId(BigDecimal.valueOf(155D));
        direccionNuevo.setFechaCreacion(new Date());
        direccionNuevo.setCiudadId(ciudad);
        try {
            direccionFacade.create(direccionNuevo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Guardado Exitoso!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error al Guardar!"));
        }
        cargar();
        limpiar();
    }

    public void update() {
        direccion.setCiudadId(ciudad);
        try {
            direccionFacade.edit(direccion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Registro modificado!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error al Actualizar!"));
        }
        cargar();
        limpiar();
    }

    public void eliminar() {
        try{
        direccionFacade.remove(direccion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","¡Eliminado!"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","¡Error al Eliminar!"));
        }
        cargar();
        limpiar();
    }

    public void limpiar() {
        direccionNuevo = null;
        direccionNuevo = new Direccion();
        direccion = null;
        direccion = new Direccion();
        ciudad = null;
        ciudad = new Ciudad();
    }

    public List<Direccion> getDireccionList() {
        return direccionList;
    }

    public void setDireccionList(List<Direccion> direccionList) {
        this.direccionList = direccionList;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    public Direccion getDireccionNuevo() {
        return direccionNuevo;
    }

    public void setDireccionNuevo(Direccion direccionNuevo) {
        this.direccionNuevo = direccionNuevo;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

}
