package com.user_service.service;

import com.user_service.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<User> getAllUser();
    public List<User> getUserById(List<Long> id);
    public User updateUser(User user);
    public Long saveUser(User user);

}
