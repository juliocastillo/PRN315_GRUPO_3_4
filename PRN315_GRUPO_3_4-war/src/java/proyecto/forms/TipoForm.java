/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.forms;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import proyecto.ejb.TipoFacadeLocal;
import proyecto.entidades.Tipo;

/**
 *
 * @author Dell
 */
@Named(value = "tipoForm")
@Dependent
public class TipoForm {

    @EJB
    private TipoFacadeLocal tipoFacade;
    private List<Tipo> tipoList;

    @PostConstruct
    public void init() {
        try {
            tipoList = tipoFacade.findAll();
        } catch (Exception e) {
        }
    }

    public TipoForm() {
    }

    public List<Tipo> getTipoList() {
        return tipoList;
    }

    public void setTipoList(List<Tipo> tipoList) {
        this.tipoList = tipoList;
    }

}
