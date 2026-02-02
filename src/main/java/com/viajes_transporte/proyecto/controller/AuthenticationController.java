package com.viajes_transporte.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viajes_transporte.proyecto.models.AuthLoginRequestDTO;
import com.viajes_transporte.proyecto.service.UserDetailsServiceImp;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest),HttpStatus.OK);
    }
    
}
