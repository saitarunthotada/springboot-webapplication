package com.springboot.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.webapplication.model.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    public User findByUsername(String username);
    public User getUserByUsername(String username);
    public User deleteByUsername(String username);
}
