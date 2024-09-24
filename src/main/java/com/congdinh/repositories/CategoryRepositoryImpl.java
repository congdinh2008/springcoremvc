package com.congdinh.repositories;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.congdinh.entities.Category;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {
    private final SessionFactory sessionFactory;

    public CategoryRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Category", Category.class).getResultList();
    }

    @Override
    public Category findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Category.class, id);
    }

    @Override
    public void save(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(category);
    }

    @Override
    public void update(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(category);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.get(Category.class, id);
        session.remove(category);
    }

}
