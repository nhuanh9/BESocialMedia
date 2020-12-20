package com.example.media.model.entity;

import com.example.media.model.User;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date createAt;

    @Column(nullable = false)
    private int status;

    @Column(nullable = true)
    private String notification;

    @Column(nullable = true)
    private String img;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PostEntity() {
    }

    public PostEntity(Date createAt, int status, String notification, String img, User user) {
        this.createAt = createAt;
        this.status = status;
        this.notification = notification;
        this.img = img;
        this.user = user;
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

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
