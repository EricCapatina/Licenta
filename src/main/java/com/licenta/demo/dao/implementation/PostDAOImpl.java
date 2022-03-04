package com.licenta.demo.dao.implementation;

import java.util.List;

import com.licenta.demo.dao.PostDAO;
import com.licenta.demo.database.entity.Post;
import com.licenta.demo.database.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Repository
public class PostDAOImpl extends AbstractDAOImpl<Post> implements PostDAO {

   // final static Logger log = Logger.getLogger(TaskDAOImpl.class.getName());

    @Override
    public List<Post> getUserPosts(String userName) {
        List<Post> tasks = null;
        try (Session session = getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("userName"), userName));
            User user = session.createQuery(criteriaQuery).uniqueResult();
            tasks = user.getPosts();
        } catch (Exception e) {
            //log.error("Cannot get user tasks", e);
        }
        return tasks;
    }

    @Override
    protected Class<Post> getEntityClass() {
        return Post.class;
    }
}
