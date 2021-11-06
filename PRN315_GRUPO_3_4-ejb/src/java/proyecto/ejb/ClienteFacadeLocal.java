/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.ejb;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import proyecto.entidades.Cliente;

/**
 *
 * @author Dell
 */
@Local
public interface ClienteFacadeLocal {

    void create(Cliente cliente);

    void edit(Cliente cliente);

    void remove(Cliente cliente);

    Cliente find(Object id);

    List<Cliente> findAll();

    List<Cliente> findRange(int[] range);
    
    List<Cliente> clientePorTipo(BigDecimal tipoId);
    
    List<Cliente> clientePorTienda(BigDecimal TiendaId);
    
    List<Cliente> clientePorPais(BigDecimal paisId);
    
    long countClientePorTipo(BigDecimal tipoId);
    

    int count();
    
}
