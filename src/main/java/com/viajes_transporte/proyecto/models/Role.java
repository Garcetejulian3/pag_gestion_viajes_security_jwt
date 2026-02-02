package com.viajes_transporte.proyecto.models;

import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    //Usamos Set porque no permite repetidos
    //List permite repetidos
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
    @JoinTable (name = "roles_permissions", joinColumns = @JoinColumn(name = "role_id"),
                inverseJoinColumns=@JoinColumn(name = "permission_id"))
    private Set<Permission> permissionsList = new HashSet<>();
}