/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.dao;

import business.entity.City;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bnc
 */
public class CityJpaDao implements JpaDao<City>{
    
    private final EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    public EntityManager entityManager() {
        return entityManager;}

    @Override
    public City get(Integer id) {
        return entityManager.find(City.class, id);}

    @Override
    public List<City> getAll() {
        Query query = entityManager.createNamedQuery("City.findAll");
        return query.getResultList();}

    @Override
    public void save(City city) {
    executeInsideTransaction(em->em.persist(city));}

    @Override
    public void update(City city) {
        executeInsideTransaction(em->em.merge(city));}

    @Override
    public void delete(City city) {
        executeInsideTransaction(em->em.remove(city));}
    
}
