package com.user_service.repository;

import com.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query(value = " select * from user where user_id in :ids ",nativeQuery = true)
    public List<User> getUsersByIds(List<Long> ids);
}
