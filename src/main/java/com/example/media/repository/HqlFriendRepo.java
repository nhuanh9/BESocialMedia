package com.example.media.repository;

import com.example.media.model.entity.FriendEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Repository
public class HqlFriendRepo {
    @PersistenceContext
    private EntityManager entityManager;

//    public Session getSession() {
//        Session session = entityManager.unwrap(Session.class);
//        return session;
//    }

    public List<Long> findAllFriendById(Long id) {
//        Session session = getSession();
        String hql = "SELECT FriendEntity.idUserFriend FROM FriendEntity join  FriendEntity.user where User.id= 1";
//        String hql = "SELECT * FROM FriendEntity";
        TypedQuery<Long> query = (TypedQuery<Long>) entityManager.createQuery(hql,Long.class);
//        query.setParameter("email", name);
//        Query<FriendEntity> query = session.createQuery(hql, FriendEntity.class);
        try {
            return query.getResultList();
        } catch (NoResultException n) {
            return null;
        }
    }
}
