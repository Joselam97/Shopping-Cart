package org.servlet.webapp.servlet.models;

//Clase que representa a un usuario con los siguientes parametros
public class Usuario {
    private Long id;
    private String username;
    private String password;
    private String email;

    //obtiene el id
    public Long getId() {
        return id;
    }

    //establece el id
    public void setId(Long id) {
        this.id = id;
    }

    //obtiene el username
    public String getUsername() {
        return username;
    }

    //establece el username
    public void setUsername(String username) {
        this.username = username;
    }

    //obtiene el password
    public String getPassword() {
        return password;
    }

    //establece el password
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
