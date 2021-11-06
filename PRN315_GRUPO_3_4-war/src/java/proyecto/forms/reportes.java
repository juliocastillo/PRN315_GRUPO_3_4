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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import proyecto.ejb.ClienteFacadeLocal;
import proyecto.ejb.PaisFacadeLocal;
import proyecto.ejb.TiendaFacadeLocal;
import proyecto.ejb.TipoFacadeLocal;
import proyecto.entidades.Cliente;
import proyecto.entidades.Pais;
import proyecto.entidades.Tienda;
import proyecto.entidades.Tipo;

/**
 *
 * @author Dell
 */
@Named(value = "reportes")
@SessionScoped
public class reportes implements Serializable {

    @EJB
    private PaisFacadeLocal paisFacade;

    @EJB
    private TiendaFacadeLocal tiendaFacade;

    @EJB
    private TipoFacadeLocal tipoFacade;

    @EJB
    private ClienteFacadeLocal clienteFacade;

    private List<Cliente> clienteListCompleta;

    private List<Cliente> clientePorTipo;

    private List<Cliente> clientePorTienda;

    private List<Cliente> clientePorPais;

    private List<plantillaReporte> clienteTable;
    
    private List<Tipo> tipoList;
    
    private List<Tienda> tiendaList;
    
    private List<Pais> paisList;
    
    private BigDecimal Id;
    
    private List<plantillaContador> contador;

    @PostConstruct
    public void init() {
        contador = new ArrayList<>();
        clienteTable = new ArrayList<>();
    }
    public void cargar(){
        try{
            tipoList = tipoFacade.findAll();
            tiendaList = tiendaFacade.findAll();
            paisList = paisFacade.findAll();
        }catch(Exception e){
            
        }
    }
    public void limpiar(){
        clienteTable = null;
        clienteTable = new ArrayList<>();
        contador = null;
        contador = new ArrayList<>();
    }
    

    public void listaCompleta() {
        limpiar();
        try {
            clienteListCompleta = clienteFacade.findAll();
        } catch (Exception e) {
        }
        llenartabla(clienteListCompleta);
    }
    public void listaPorTipo() {
        cargar();
        limpiar();
        try {
            clientePorTipo = clienteFacade.clientePorTipo(Id);
        } catch (Exception e) {
        }
        llenartabla(clientePorTipo);
    }
    
    public void listaPorTienda() {
        cargar();
        limpiar();
        try {
            clientePorTienda = clienteFacade.clientePorTienda(Id);
        } catch (Exception e) {
        }
        llenartabla(clientePorTienda);
    }
    public void listaPorPais() {
        cargar();
        limpiar();
        try {
            clientePorPais = clienteFacade.clientePorPais(Id);
        } catch (Exception e) {
        }
        llenartabla(clientePorPais);
    }
    public void llenartabla(List<Cliente> clienteList) {
        for (int i = 0; i < clienteList.size(); i++) {
            plantillaReporte pl = new plantillaReporte();
            pl.setIdCliente("" + clienteList.get(i).getClienteId());
            pl.setDireccion(clienteList.get(i).getDireccionId().getDireccion());
            pl.setTienda(clienteList.get(i).getTiendaId().getNombre());
            pl.setNombre(clienteList.get(i).getNombres() + " " + clienteList.get(i).getApellidos());
            String cadena = "";
            for (Tipo t : clienteList.get(i).getTipoList()) {
                cadena = cadena + "-" + t.getNombre() + "\n";
            }
            pl.setTipo(cadena);
            if (clienteList.get(i).getActivo() == 1) {
                pl.setActivo("Activo");
            } else {
                pl.setActivo("Inactivo");
            }
            pl.setFecha("" + clienteList.get(i).getFechaCreacion());
            clienteTable.add(pl);
        }
    }
    public void cout(){
        cargar();
        limpiar();
        for(Tipo t:tipoList){
            plantillaContador p = new plantillaContador();
            p.setTipo(t.getNombre());
            p.setCantidad(clienteFacade.countClientePorTipo(t.getTipoId()));
            contador.add(p);
        }
    }

    public reportes() {
    }

    public List<plantillaReporte> getClienteTable() {
        return clienteTable;
    }

    public List<Tipo> getTipoList() {
        return tipoList;
    }

    public void setTipoList(List<Tipo> tipoList) {
        this.tipoList = tipoList;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal Id) {
        this.Id = Id;
    }

    public List<Tienda> getTiendaList() {
        return tiendaList;
    }

    public void setTiendaList(List<Tienda> tiendaList) {
        this.tiendaList = tiendaList;
    }

    public List<Cliente> getClienteListCompleta() {
        return clienteListCompleta;
    }

    public void setClienteListCompleta(List<Cliente> clienteListCompleta) {
        this.clienteListCompleta = clienteListCompleta;
    }

    public List<Cliente> getClientePorTipo() {
        return clientePorTipo;
    }

    public void setClientePorTipo(List<Cliente> clientePorTipo) {
        this.clientePorTipo = clientePorTipo;
    }

    public List<Cliente> getClientePorTienda() {
        return clientePorTienda;
    }

    public void setClientePorTienda(List<Cliente> clientePorTienda) {
        this.clientePorTienda = clientePorTienda;
    }

    public List<Cliente> getClientePorPais() {
        return clientePorPais;
    }

    public void setClientePorPais(List<Cliente> clientePorPais) {
        this.clientePorPais = clientePorPais;
    }

    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    public List<plantillaContador> getContador() {
        return contador;
    }

    public void setContador(List<plantillaContador> contador) {
        this.contador = contador;
    }

}
