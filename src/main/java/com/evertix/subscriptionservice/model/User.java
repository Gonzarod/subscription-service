package com.evertix.subscriptionservice.model;


import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
public class User {

    public User(String username, String password, String email, String name,
                String lastName, String dni, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.phone = phone;

    }

    private Long id;

    private String username;

    private String password;

    private String email;

    private String name;

    private String lastName;

    private String dni;

    private String phone;


}