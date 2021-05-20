/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.dao;

import java.util.List;

/**
 *
 * @author bnc
 */
public interface Dao<E> {
    E get(Integer id);
    
    List<E> getAll();
    
    void save(E e);
    
    void update(E e);
    
    void delete(E e);
}
