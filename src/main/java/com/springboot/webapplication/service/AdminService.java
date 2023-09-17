package com.springboot.webapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.webapplication.model.Admin;
import com.springboot.webapplication.model.User;
import com.springboot.webapplication.repository.AdminRepository;
import com.springboot.webapplication.repository.UserRepository;

@Service
public class AdminService 
{
    @Autowired
    private final AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void registerAdmin(Admin admin)
    {
        adminRepository.save(admin);
    }

    public Admin updateAdminDetails(Admin admin)
    {
     return adminRepository.save(admin);
    }

    public Admin getAdminByUsername(String username)
    {
        return adminRepository.getAdminByUsername(username);
    }

    public void deleteAdminByUsername(Admin admin)
    {   
        admin = adminRepository.getAdminByUsername(admin.getUsername());
        adminRepository.delete(admin);
    }

    public List<Admin> getAllAdminDetails()
    {
        return adminRepository.findAll();
    }

    //User Profile
    public User getUserByUsername(String username)
    {
        return userRepository.getUserByUsername(username);
    }
    
}