/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.ejb;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import proyecto.entidades.Cliente;

/**
 *
 * @author Dell
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {

    @PersistenceContext(unitName = "PRN315_GRUPO_3_4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    public List<Cliente> clientePorTipo(BigDecimal tipoId) {
        try {
            return em.createNamedQuery("Cliente.findByTipo").setParameter("tipoId", tipoId).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public List<Cliente> clientePorTienda(BigDecimal TiendaId) {
        try {
            return em.createNamedQuery("Cliente.findByTienda").setParameter("tiendaId", TiendaId).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public List<Cliente> clientePorPais(BigDecimal paisId) {
        try {
            return em.createNamedQuery("Cliente.findByPais").setParameter("paisId", paisId).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public long countClientePorTipo(BigDecimal tipoId) {
        try{
            return (long) em.createNamedQuery("Cliente.CountByTipo").setParameter("tipoId", tipoId).getSingleResult();
        }catch(Exception e){
            System.out.println(e.toString());
            return 0;
        }
    }
}
