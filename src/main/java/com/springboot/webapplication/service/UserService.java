package com.springboot.webapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.webapplication.model.User;
import com.springboot.webapplication.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private KafkaTemplate<String, String> kafkaTemplate;
    private String TOPIC = "webapplication-user-topic";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, KafkaTemplate<String, String> kafkaTemplate)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void registerUser(User user)
    {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        userRepository.save(user);
        kafkaTemplate.send(TOPIC,"user-registered", user.getUsername());
    }

    public User viewUserDetails(String username)
    {
        User user = userRepository.findByUsername(username);
        if(user==null)
        {
            return null;
        }
        else{
            return user;
        }
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
            throw new UsernameNotFoundException("User not found");
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
