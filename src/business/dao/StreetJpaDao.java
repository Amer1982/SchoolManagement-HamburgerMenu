
package business.dao;


import business.entity.Street;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;


public class StreetJpaDao implements JpaDao<Street>{
    
    private final EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    public EntityManager entityManager() {
        return entityManager;
    }

    @Override
    public void executeInsideTransaction(Consumer<EntityManager> consumer) {
        JpaDao.super.executeInsideTransaction(consumer); 
    }

    @Override
    public Street get(Integer id) {
        return entityManager.find(Street.class, id);
                }

    @Override
    public List<Street> getAll() {
        Query query = entityManager.createNamedQuery("Street.findAll");
        return query.getResultList();}

    @Override
    public void save(Street street) {
        executeInsideTransaction(em->em.persist(street));}

    @Override
    public void update(Street street) {
        executeInsideTransaction(em->em.merge(street));}

    @Override
    public void delete(Street street) {
        executeInsideTransaction(em->em.remove(street));}
    
    public Street findByStreet(String streetName) {
        if (streetName == null || streetName.isEmpty()) {
            return null;
        }
        Query query = entityManager.createNamedQuery("Street.findByStreet");
        query.setParameter("street", streetName);
        
        try {
            Street street = (Street) query.getParameterValue(streetName);
            return street;
        } catch (NoResultException exception) {
            System.err.format("Not exist user with username '%s'%n", streetName);
            return null;
        } catch (NonUniqueResultException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
    
}
