package com.licenta.demo.dao.implementation;


import com.licenta.demo.dao.UserDAO;
import com.licenta.demo.database.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {

//    final static Logger log = Logger.getLogger(AbstractDAOImpl.class.getName());
    private static final String USERNAME = "userName";

    @SuppressWarnings("unchecked")
    @Override
    public User getUserByUsername(String username) {
        User user = null;
        try (Session session = getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
            Root<User> root = criteriaQuery.from(getEntityClass());
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get(USERNAME), username));
            user = session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
//            log.error(e);
        }
        return user;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected Class getEntityClass() {
        return User.class;
    }

}