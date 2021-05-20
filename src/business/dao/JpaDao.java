/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.dao;

import javax.persistence.EntityManager;

/**
 *
 * @author bnc
 */
public interface JpaDao<E> extends Dao<E>{
    
    public EntityManager getEntityManager();
    
    
}
