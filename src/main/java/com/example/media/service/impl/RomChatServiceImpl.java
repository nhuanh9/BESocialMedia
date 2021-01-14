package com.example.media.service.impl;

import com.example.media.model.entity.RomChatEntity;
import com.example.media.repository.IRomChatRepo;
import com.example.media.service.IRomChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RomChatServiceImpl implements IRomChatService {
    @Autowired
    IRomChatRepo iRomChatRepo;

    @Override
    public void save(RomChatEntity romChatEntity) {
        iRomChatRepo.save(romChatEntity);
    }

    @Override
    public RomChatEntity findRomChat(String userName1, String userName2) {
        return iRomChatRepo.findChatRom(userName1,userName2);
    }
}
