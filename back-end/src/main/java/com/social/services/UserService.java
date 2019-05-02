package com.social.services;

import com.social.entities.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User update(User user);

    User find(String userName);

    User find(Long id);
}
