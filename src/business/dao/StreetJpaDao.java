
package business.dao;


import business.entity.Admin;
import business.entity.Street;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
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
    
}
