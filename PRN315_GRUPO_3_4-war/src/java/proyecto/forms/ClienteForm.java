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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import proyecto.ejb.ClienteFacadeLocal;
import proyecto.ejb.DireccionFacadeLocal;
import proyecto.ejb.TiendaFacadeLocal;
import proyecto.ejb.TipoFacadeLocal;
import proyecto.entidades.Cliente;
import proyecto.entidades.Direccion;
import proyecto.entidades.Tienda;
import proyecto.entidades.Tipo;

/**
 *
 * @author Dell
 */
@Named(value = "clienteForm")
@SessionScoped
public class ClienteForm implements Serializable {

    @EJB
    private TipoFacadeLocal tipoFacade;

    @EJB
    private TiendaFacadeLocal tiendaFacade;

    @Inject
    private DireccionFacadeLocal direccionFacade;

    @Inject
    private ClienteFacadeLocal clienteFacade;

    private List<Tienda> tiendaList;

    private List<Direccion> direccionList;

    private List<Cliente> clienteList;
    
    private List<Tipo> tipoList;
    
    private List<Tipo> list;

    private Tienda tienda;

    private Direccion direccion;

    private Cliente clienteNuevo;

    private Cliente clienteU;
    
    private Tipo tipo;

    @PostConstruct
    public void init() {
        tienda = new Tienda();
        direccion = new Direccion();
        clienteNuevo = new Cliente();
        tipo = new Tipo();
        list = new ArrayList<>();
        cargar();
    }

    public void cargar() {
        clienteList = clienteFacade.findAll();
        tiendaList = tiendaFacade.findAll();
        direccionList = direccionFacade.findAll();
        tipoList = tipoFacade.findAll();
    }

    public void limpiar() {
        tienda = null;
        tienda = new Tienda();
        direccion = null;
        direccion = new Direccion();
        clienteNuevo = null;
        clienteNuevo = new Cliente();
        tipo = null;
        tipo = new Tipo();
        list = null;
        list = new ArrayList<>();
        clienteU = null;
        clienteU = new Cliente();
    }

    public void update() {
        clienteU.setTiendaId(tienda);
        clienteU.setDireccionId(direccion);
        try {
            clienteFacade.edit(clienteU);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Registro modificado!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error al Actualizar!"));
        }
        cargar();
        limpiar();
    }
    public void eliminar(){
        try{
            clienteFacade.remove(clienteU);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","¡Eliminado!"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","¡Error al Eliminar!"));
        }
        cargar();
    }
    public void updateTipoCliente(){
        clienteU.getTipoList().add(tipo);
        try {
            clienteFacade.edit(clienteU);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Registro modificado!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error al Actualizar!"));
        }
        cargar();
        limpiar();
    }

    public void guardar() {
        list.add(tipo);
        clienteNuevo.setTipoList(list);
        clienteNuevo.setClienteId(BigDecimal.valueOf(155D));
        clienteNuevo.setFechaCreacion(new Date());
        clienteNuevo.setDireccionId(direccion);
        clienteNuevo.setTiendaId(tienda);
        try {
            clienteFacade.create(clienteNuevo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Guardado Exitoso!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error al Guardar!"));
        }
        cargar();
        limpiar();
    }

    public void leerRow(Cliente cliente) {
        this.clienteU = cliente;
    }

    public ClienteForm() {
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public List<Tienda> getTiendaList() {
        return tiendaList;
    }

    public void setTiendaList(List<Tienda> tiendaList) {
        this.tiendaList = tiendaList;
    }

    public List<Direccion> getDireccionList() {
        return direccionList;
    }

    public void setDireccionList(List<Direccion> direccionList) {
        this.direccionList = direccionList;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Cliente getClienteNuevo() {
        return clienteNuevo;
    }

    public void setClienteNuevo(Cliente clienteNuevo) {
        this.clienteNuevo = clienteNuevo;
    }

    public Cliente getClienteU() {
        return clienteU;
    }

    public void setClienteU(Cliente clienteU) {
        this.clienteU = clienteU;
    }

    public List<Tipo> getTipoList() {
        return tipoList;
    }

    public void setTipoList(List<Tipo> tipoList) {
        this.tipoList = tipoList;
    }

    public List<Tipo> getList() {
        return list;
    }

    public void setList(List<Tipo> list) {
        this.list = list;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
