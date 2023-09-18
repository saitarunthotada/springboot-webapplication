package com.springboot.webapplication.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.springboot.webapplication.model.Admin;
import com.springboot.webapplication.model.User;
import com.springboot.webapplication.service.AdminService;
import com.springboot.webapplication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@Validated
public class AdminController {

    @Autowired
    private final AdminService adminService;

    @Autowired
    private UserService userService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin-register")
    public String index(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "admin-register";
    }

    @PostMapping("/admin-register")
    public String adminRegister(@ModelAttribute("admin") @Validated Admin admin, BindingResult result) {
        if (result.hasErrors()) {
            return "admin-register"; // Return to the form with validation errors
        }

        adminService.registerAdmin(admin);
        return "admin-login";
    }

    @GetMapping("/admin-login")
    public String adminHomePage(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "admin-login";
    }

    @PostMapping("/admin-login/profile")
    public String getAdminDetails(@RequestParam("username") String username, @RequestParam String password, Model model) {
        Admin admin = adminService.getAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            model.addAttribute("admin", admin);
            return "admin-profile";
        } else {
            model.addAttribute("logicerror", "invalid username or password");
        }
        return "redirect:/admin-login?error=true";
    }

    @GetMapping("/admin-edit")
    public String showUpdatePage(@RequestParam String username, Model model) {
        Admin admin = adminService.getAdminByUsername(username);
        if (admin != null) {
            model.addAttribute("admin", admin);
            return "admin-update";
        }
        return "error"; // Handle case when admin is not found
    }

    @PostMapping("/admin-edit")
    public String updateAdminDetails(@ModelAttribute @Validated Admin updatedAdmin, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin-update"; // Return to the form with validation errors
        }

        updatedAdmin = adminService.updateAdminDetails(updatedAdmin);
        if (updatedAdmin != null) {
            model.addAttribute("admin", updatedAdmin);
            return "admin-profile";
        } else {
            return "error"; // Handle case when admin is not found
        }
    }

    @GetMapping("/admin-list")
    public String getAllAdmins(Model model) {
        List<Admin> admins = adminService.getAllAdminDetails();
        model.addAttribute("admins", admins);
        return "admin-list";
    }

    @GetMapping("/user-list")
    public String getAllUsers(Model model)
    {
        List<User> users = userService.getAllUserDetails();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "admin-login";
    }
}
