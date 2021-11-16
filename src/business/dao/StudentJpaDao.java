/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.dao;

import business.entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author bnc
 */
public class StudentJpaDao implements JpaDao<Student>{
    
     private final EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    public EntityManager entityManager() {
        return entityManager;
    }

    @Override
    public Student get(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getAll() {
        //Ovako postavljam bez namedQuerry
        //Query query = entityManager.createQuery("SELECT e FROM Student s");
        Query query = entityManager.createNamedQuery("Student.findAll");
        return query.getResultList();
    }

    @Override
    public void save(Student student) {
        executeInsideTransaction(em->em.persist(student));
        }

    @Override
    public void update(Student student) {
        executeInsideTransaction(em->em.merge(student));
        }

    @Override
    public void delete(Student student) {
        
        executeInsideTransaction(em -> em.remove(em.contains(student)?student:em.merge(student)));
    }   
}
