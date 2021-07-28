/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.dao;

import business.entity.Country;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bnc
 */
public class CountryJpaDao implements JpaDao<Country>{
    
     private final EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    public EntityManager entityManager() {
        return entityManager();
                }

    @Override
    public void executeInsideTransaction(Consumer<EntityManager> consumer) {
        JpaDao.super.executeInsideTransaction(consumer); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Country get(Integer id) {
        return entityManager.find(Country.class, id);}

    @Override
    public List<Country> getAll() {
        Query query = entityManager.createNamedQuery("Country.findAll");
        return query.getResultList();}

    @Override
    public void save(Country country) {
        executeInsideTransaction(em->em.persist(country));}

    @Override
    public void update(Country country) {
        executeInsideTransaction(em->em.merge(country));}

    @Override
    public void delete(Country country) {
        executeInsideTransaction(em->em.remove(country));}
    
}
