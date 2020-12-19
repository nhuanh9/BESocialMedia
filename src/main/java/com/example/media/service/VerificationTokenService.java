package com.example.media.service;

import com.example.media.model.VerificationToken;

public interface
VerificationTokenService {
    VerificationToken findByToken(String token);

    void save(VerificationToken token);
}