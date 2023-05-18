package org.example.KursachP.controllers;

import org.example.KursachP.models.Employee;
import org.example.KursachP.security.EmployeeDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class authController {
@GetMapping("/login")
    public String loginPage(){
    return "/auth/login";
    }
@GetMapping("/registration")
    public String registrationPage(@ModelAttribute("employee") Employee employee){

    return "/auth/registration";
    }
    @GetMapping("/admin")
    public String adminPage(){

    return "/auth/admin";
    }

    @GetMapping("/show")
    public String show(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDetails employeeDetails = (EmployeeDetails) authentication.getPrincipal();
        System.out.println(employeeDetails.getEmployee().getRole());

        return "systemMenu";
    }

}
