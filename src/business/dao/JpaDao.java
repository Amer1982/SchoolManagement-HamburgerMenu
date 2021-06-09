/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.dao;

import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import utils.Constants;

/**
 *
 * @author bnc
 * @param <E>
 */
public interface JpaDao<E> extends Dao<E>{
    
    
    EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(Constants.PU_NAME);//persistenceUnitName

    public EntityManager entityManager();
    
    default void executeInsideTransaction(Consumer<EntityManager> consumer){
        EntityManager entityManager = entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            consumer.accept(entityManager);
            transaction.commit();
        }catch(Exception e){
            transaction.rollback();
            throw new RuntimeException(e.getMessage());
        }    
    }
}