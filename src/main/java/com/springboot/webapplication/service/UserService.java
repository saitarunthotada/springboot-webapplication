package com.springboot.webapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.webapplication.model.User;
import com.springboot.webapplication.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void registerUser(User user)
    {
        userRepository.save(user);
    }

    public User viewUserDetails(String username)
    {
        return userRepository.findByUsername(username);
    }

    public User getUser(String username)
    {
        return userRepository.getUserByUsername(username);
    }

    public User updateUser(User updateUser)
    {
        User existUser = userRepository.findByUsername(updateUser.getUsername());
        if(existUser!=null)
        {
        return userRepository.save(updateUser);
        }
        else 
        {
            return null;
        }
    }
    public void deleteUser(User user)
    {   
        user = userRepository.deleteByUsername(user.getUsername());
        userRepository.delete(user);
    }
    
    public List<User> getAllUserDetails()
    {
        return userRepository.findAll();
    }


}
