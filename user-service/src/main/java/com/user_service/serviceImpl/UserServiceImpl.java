package com.user_service.serviceImpl;

import com.user_service.entity.User;
import com.user_service.repository.UserRepo;
import com.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getUserById(List<Long> userIds) {
        return userRepo.getUsersByIds(userIds);
    }

    @Override
    public User updateUser(User user) {
     return null;
    }

    @Override
    public Long saveUser(User user) {
        return userRepo.save(user).getUserId();
    }
}
