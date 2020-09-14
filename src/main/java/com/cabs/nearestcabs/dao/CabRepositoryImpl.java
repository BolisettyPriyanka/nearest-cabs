package com.cabs.nearestcabs.dao;

import com.cabs.nearestcabs.web.model.Cab;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class CabRepositoryImpl implements CabRepository {
    @Override
    public List<Cab> searchNearestCabs(float latitude, float longitude, int limit, float radius) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
        EntityManager entitymanager = emfactory.createEntityManager();

        //Scalar function
        Query query = entitymanager.
                createQuery("select * from Cab e " +
                        "where latitude >= " + latitude
                        + " and longitude <= " + longitude);
        List<Cab> list = query.getResultList();

        for(Cab e:list) {
            System.out.println("Cab id :"+e);
        }
        return list;
    }

    @Override
    public List<Cab> findAll() {
        return null;
    }

    @Override
    public List<Cab> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Cab> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Cab> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Cab cab) {

    }

    @Override
    public void deleteAll(Iterable<? extends Cab> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Cab> S save(S s) {
        return null;
    }

    @Override
    public <S extends Cab> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Cab> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Cab> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Cab> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Cab getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Cab> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Cab> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Cab> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Cab> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Cab> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Cab> boolean exists(Example<S> example) {
        return false;
    }
}
