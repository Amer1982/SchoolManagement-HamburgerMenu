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
    
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
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
    public void save(Student e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Student e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Student e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
