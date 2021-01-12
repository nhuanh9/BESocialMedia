package com.example.media.model;

import com.example.media.model.entity.PostEntity;
import lombok.Data;

@Data
public class UserLikePost {
    private User user;
    private PostEntity postEntity;
    private boolean isLiked;
}
