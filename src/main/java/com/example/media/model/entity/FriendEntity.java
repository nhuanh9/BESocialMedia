package com.example.media.model.entity;

import com.example.media.model.User;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "friend")
public class FriendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date createAt;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private Long idUserFriend;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public FriendEntity() {
    }

    public FriendEntity(Date createAt, int status, User user, Long idUserFriend) {
        this.createAt = createAt;
        this.status = status;
        this.user = user;
        this.idUserFriend = idUserFriend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getIdUserFriend() {
        return idUserFriend;
    }

    public void setIdUserFriend(Long idUserFriend) {
        this.idUserFriend = idUserFriend;
    }
}
