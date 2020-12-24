package com.example.media.repository;

import com.example.media.model.entity.FriendEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class HqlFriendRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public List<FriendEntity> findAllFriendById(Long id) {
        String hql = "SELECT f FROM FriendEntity f inner join  f.user u where u.id=:id";
        TypedQuery<FriendEntity> query =  entityManager.createQuery(hql,FriendEntity.class);
        query.setParameter("id", id);
        try {
            return query.getResultList();
        } catch (NoResultException n) {
            return null;
        }
    }
}
