package com.example.media.model;

import com.example.media.model.entity.ImgEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PostModel {
    private Long id;
    private Date createAt;
    private int status;
    private String notification;
    private String content;
    private Long postIdShear;
    private User user;
    private String imgs;

    public PostModel(Long id, Date createAt, int status, String notification, String content, Long postIdShear, User user, String imgs) {
        this.id = id;
        this.createAt = createAt;
        this.status = status;
        this.notification = notification;
        this.content = content;
        this.postIdShear = postIdShear;
        this.user = user;
        this.imgs = imgs;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostIdShear() {
        return postIdShear;
    }

    public void setPostIdShear(Long postIdShear) {
        this.postIdShear = postIdShear;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public PostModel() {
    }
}

