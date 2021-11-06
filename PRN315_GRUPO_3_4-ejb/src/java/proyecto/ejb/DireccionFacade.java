/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import proyecto.entidades.Direccion;

/**
 *
 * @author Dell
 */
@Stateless
public class DireccionFacade extends AbstractFacade<Direccion> implements DireccionFacadeLocal {

    @PersistenceContext(unitName = "PRN315_GRUPO_3_4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DireccionFacade() {
        super(Direccion.class);
    }
    
}
