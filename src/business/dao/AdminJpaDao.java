
package business.dao;


import business.entity.Admin;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class AdminJpaDao implements JpaDao<Admin>{
    
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
    public Admin get(Integer id) {
        return entityManager.find(Admin.class, id);
                }

    @Override
    public List<Admin> getAll() {
        Query query = entityManager.createNamedQuery("Admin.findAll");
        return query.getResultList();}
    
    public List<Admin> getByID() {
        Query query = entityManager.createNamedQuery("Admin.findById");
        return query.getResultList();}
    
    public List<Admin> sumOfSalary() {
        Query query = entityManager.createNamedQuery("Admin.salary");
        return query.getResultList();}

    @Override
    public void save(Admin admin) {
        executeInsideTransaction(em->em.persist(admin));}

    @Override
    public void update(Admin admin) {
        executeInsideTransaction(em->em.merge(admin));}

    @Override
    public void delete(Admin admin) {
        executeInsideTransaction(em -> em.remove(em.contains(admin)?admin:em.merge(admin)));
    }
    
}
