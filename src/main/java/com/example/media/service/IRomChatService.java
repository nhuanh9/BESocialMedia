package com.example.media.service;

import com.example.media.model.entity.PostEntity;
import com.example.media.model.entity.RomChatEntity;

public interface IRomChatService {
    void save(RomChatEntity romChatEntity);
    RomChatEntity findRomChat(String userName1,String userName2);
}
