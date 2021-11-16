/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.dao;


import business.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author bnc
 */
public class UserJpaDao implements JpaDao<User>{
    
   private final EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    public EntityManager entityManager() {
        return entityManager;
    }

    @Override
    public User get(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        Query query = entityManager.createNamedQuery("User.findAll");
        return query.getResultList();
    }

    @Override
    public void save(User user) {
        /*EntityTransaction entityTransaction = entityManager.getTransaction();
       try {
        entityTransaction.begin();
        entityManager.persist(user);
        entityTransaction.commit();
        } catch (Exception e) {
                entityTransaction.rollback();
            throw new RuntimeException(e.getMessage());
        }*/
        
        //umjesto ovoga, da se ne bi ponavljao radim default metodu u JpaDao u pozivam
 
        executeInsideTransaction(em -> em.persist(user));
    }

    @Override
    public void update(User user) {
        executeInsideTransaction(em -> em.merge(user));
    }
    
    @Override
    public void delete(User user) {
        executeInsideTransaction(em -> em.remove(em.contains(user)?user:em.merge(user)));
    }
    
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        Query query = entityManager.createNamedQuery("User.findByUsernameAndPassword");
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException exception) {
            System.err.format("User with username "+ username+" doesn't exist '%s'%n");
            return null;
        } catch (NonUniqueResultException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
    
    public User findByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return null;
        }
        Query query = entityManager.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException exception) {
            System.err.format("Not exist user with username '%s'%n", username);
            return null;
        } catch (NonUniqueResultException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
