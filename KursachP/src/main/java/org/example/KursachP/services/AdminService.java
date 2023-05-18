package org.example.KursachP.services;

import org.example.KursachP.models.Product;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AdminService {

private final ProductService productService;

    public AdminService(ProductService productService) {
        this.productService = productService;
    }
    @PreAuthorize("hasRole('ROLE_ACCOUNTANT')")
    public void doAccountant(){
    }

    @PreAuthorize("hasRole('ROLE_ACCOUNTANT') or hasRole('ROLE_ADMIN')")
    public void doAccountantAdmin(){
    }
    @PreAuthorize("hasRole('ROLE_ACCOUNTANT') or hasRole('ROLE_ADMIN') or hasRole('ROLE_DIRECTOR')")
    public void doAccountantAdminDirector(){
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIRECTOR')")
    public void doAdminDirector(){
    }

    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    public void doDirector(){
    }


}
