
package business.dao;


import business.entity.Teacher;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class TeacherJpaDao implements JpaDao<Teacher>{
    
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
    public Teacher get(Integer id) {
        return entityManager.find(Teacher.class, id);
                }

    @Override
    public List<Teacher> getAll() {
        Query query = entityManager.createNamedQuery("Teacher.findAll");
        return query.getResultList();}

    @Override
    public void save(Teacher teacher) {
        executeInsideTransaction(em->em.persist(teacher));}

    @Override
    public void update(Teacher teacher) {
        executeInsideTransaction(em->em.merge(teacher));}

    @Override
    public void delete(Teacher teacher) {
        executeInsideTransaction(em -> em.remove(em.contains(teacher)?teacher:em.merge(teacher)));
    }
    
}
