package org.servlet.webapp.servlet.models.entities;

import jakarta.persistence.*;

// Marks the class as a JPA entity and maps it to the "usuarios" table in the database
@Entity
@Table(name = "usuarios")
public class Usuario {

    // The ID of the user, which is the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generated using the identity strategy (auto-increment)
    private Long id;

    private String username;
    private String password;
    private String email;


    // Getter and setter methods for all the fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
