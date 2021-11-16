
package business.dao;


import business.entity.Subject;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;


public class SubjectJpaDao implements JpaDao<Subject>{
    
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
    public Subject get(Integer id) {
        return entityManager.find(Subject.class, id);
                }

    @Override
    public List<Subject> getAll() {
        Query query = entityManager.createNamedQuery("Subject.findAll");
        return query.getResultList();}

    @Override
    public void save(Subject subject) {
        executeInsideTransaction(em->em.persist(subject));}

    @Override
    public void update(Subject subject) {
        executeInsideTransaction(em->em.merge(subject));}

    @Override
    public void delete(Subject subject) {
        executeInsideTransaction(em->em.remove(subject));}
    
    public Subject findBySubject(String subjectName) {
        if (subjectName == null || subjectName.isEmpty()) {
            return null;
        }
        Query query = entityManager.createNamedQuery("Subject.findBySubject");
        query.setParameter("subject", subjectName);
        
        try {
            Subject subject = (Subject) query.getSingleResult();
            return subject;
        } catch (NoResultException exception) {
            System.err.format("Subject", subjectName);
            return null;
        } catch (NonUniqueResultException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
    
}
