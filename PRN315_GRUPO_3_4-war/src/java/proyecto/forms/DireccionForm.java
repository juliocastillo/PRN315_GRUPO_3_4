/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import proyecto.ejb.CiudadFacadeLocal;
import proyecto.ejb.DireccionFacadeLocal;
import proyecto.entidades.Ciudad;
import proyecto.entidades.Direccion;

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
    private Direccion direccion;
    private List<String> ciudadNombre;

    @PostConstruct
    public void init() {
        cargar();
    }

    //Metodo utilizado para cargar la tabla
    public void cargar() {
        try {
            this.direccionList = direccionFacade.findAll();
        } catch (Exception e) {

        }
    }

    public void leerFila(Direccion direccion) {
        this.direccion = direccion;
        List<Ciudad> ciudadList;
        try {
            ciudadList = ciudadFacade.findAll();
            for (Ciudad ciudad : ciudadList) {
                this.ciudadNombre.add(ciudad.getCiudad());
            }
        } catch (Exception e) {

        }
    }

    public DireccionForm() {
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

    public List<String> getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(List<String> ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }

}
