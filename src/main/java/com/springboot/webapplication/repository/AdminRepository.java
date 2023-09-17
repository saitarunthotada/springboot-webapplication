package com.springboot.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.webapplication.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
    public Admin getAdminByUsername(String username);
}
