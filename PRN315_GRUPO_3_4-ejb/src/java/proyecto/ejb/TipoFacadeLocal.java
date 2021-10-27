/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.ejb;

import java.util.List;
import javax.ejb.Local;
import proyecto.entidades.Tipo;

/**
 *
 * @author adalc
 */
@Local
public interface TipoFacadeLocal {

    void create(Tipo tipo);

    void edit(Tipo tipo);

    void remove(Tipo tipo);

    Tipo find(Object id);

    List<Tipo> findAll();

    List<Tipo> findRange(int[] range);

    int count();
    
}
