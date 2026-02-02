package com.viajes_transporte.proyecto.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viajes_transporte.proyecto.models.Role;
import com.viajes_transporte.proyecto.models.UserSec;
import com.viajes_transporte.proyecto.service.inpl.IRoleService;
import com.viajes_transporte.proyecto.service.inpl.IUserSecService;

@RestController
@RequestMapping("/api/user")
public class UserSecController {

    @Autowired
    private IUserSecService userSecService;

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity<List> getAllUsers() {
        List users = userSecService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        Optional user = userSecService.findById(id);
        return (ResponseEntity) user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserSec userSec) {

        Set<Role> roleList = new HashSet<>();
        Role readRole;

        //encriptamos contraseña
        userSec.setPassword(userSecService.encriptPassword(userSec.getPassword()));


        // Recuperar la Permission/s por su ID
        for (Role role : userSec.getRolesList()) {
            readRole = (Role) roleService.findById(role.getId()).orElse(null);
            if (readRole != null) {
                // si encuentro, guardo en la lista
                roleList.add(readRole);
            }
        }

        if (!roleList.isEmpty()) {
            userSec.setRolesList(roleList);

            UserSec newUser = userSecService.save(userSec);
            return ResponseEntity.ok(newUser);
        }
        return null;
    }

}
