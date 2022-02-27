package com.licenta.demo.dao.implementation;

import com.licenta.demo.dao.AbstractDAO;
import org.apache.logging.log4j.core.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public abstract class AbstractDAOImpl<T> implements AbstractDAO<T> {

//    final static Logger log = Logger.getLogger(AbstractDAOImpl.class.getName());

    @Autowired
    protected SessionFactory sessionFactory;

    protected Session getSession() {
        try {
            return this.sessionFactory.openSession();
        } catch (HibernateException e) {
//            log.error("Error in getSession method", e);
        }
        return null;
    }

    protected abstract Class<T> getEntityClass();

    public List<T> getAll() {
        List<T> elements = null;
        try (Session session = getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
            Root<T> root = criteriaQuery.from(getEntityClass());
            criteriaQuery.select(root);
            elements = session.createQuery(criteriaQuery).getResultList();
            session.close();
        } catch (Exception e) {
//            log.error("Cannot get information about" + getEntityClass().getSimpleName() + " entities from database", e);
        }
        return elements;
    }

    public T add(T t) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
        } catch (Exception e) {
//            log.error("Cannot add " + getEntityClass().getSimpleName(), e);
            System.out.println(e.getMessage());
        }
        return t;
    }

    public T update(T t) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.merge(t);
            transaction.commit();
        } catch (Exception e) {
//            log.error("Cannot update " + getEntityClass().getSimpleName() + " information", e);
        }
        return t;
    }

    public void remove(T t) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
        } catch (HibernateException e) {
//            log.error("Cannot remove " + getEntityClass().getSimpleName() + " information", e);
        }
    }

    @Override
    public void removeById(Long id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.delete(session.find(getEntityClass(), id));
            transaction.commit();
        } catch (HibernateException e) {
//            log.error("Cannot remove " + getEntityClass().getSimpleName() + " information", e);
        }
    }

    public T getById(Long id) {
        try (Session session = getSession()) {
            return session.find(getEntityClass(), id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
