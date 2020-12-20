package com.example.media.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

public class Friend {
    private Long id;
    private Date createAt;
    private int status;
    private Long idUser;
    private Long idUserFriend;

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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdUserFriend() {
        return idUserFriend;
    }

    public void setIdUserFriend(Long idUserFriend) {
        this.idUserFriend = idUserFriend;
    }

    public Friend(Long id, Date createAt, int status, Long idUser, Long idUserFriend) {
        this.id = id;
        this.createAt = createAt;
        this.status = status;
        this.idUser = idUser;
        this.idUserFriend = idUserFriend;
    }

    public Friend() {
    }
}
