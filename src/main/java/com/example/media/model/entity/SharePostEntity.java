package com.example.media.model.entity;

import com.example.media.model.User;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "sharePost")
public class SharePostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    public SharePostEntity() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PostEntity getPostEntity() {
        return postEntity;
    }

    public void setPostEntity(PostEntity postEntity) {
        this.postEntity = postEntity;
    }

    public SharePostEntity(Date createAt, User user, PostEntity postEntity) {
        this.createAt = createAt;
        this.user = user;
        this.postEntity = postEntity;
    }
}
