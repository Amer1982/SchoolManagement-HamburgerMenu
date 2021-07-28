
package business.dao;

import business.entity.Finance;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class FinanceJpaDao implements JpaDao<Finance>{
    
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
    public Finance get(Integer id) {
        return entityManager.find(Finance.class, id);
                }

    @Override
    public List<Finance> getAll() {
        Query query = entityManager.createNamedQuery("Finance.findAll");
        return query.getResultList();}

    @Override
    public void save(Finance finance) {
        executeInsideTransaction(em->em.persist(finance));}

    @Override
    public void update(Finance finance) {
        executeInsideTransaction(em->em.merge(finance));}

    @Override
    public void delete(Finance finance) {
        executeInsideTransaction(em->em.remove(finance));}
    
}
