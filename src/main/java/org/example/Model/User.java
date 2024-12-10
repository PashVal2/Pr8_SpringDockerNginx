package org.example.Model;

import javax.persistence.*;

@Entity
public class User { // юзер
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String name;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(nullable = false)
    private String role;
    // Конструкторы, геттеры и сеттеры
    public User() {}
    public User(Long id, String username, String password) {
        this.id = id;
        this.name = username;
        this.password = password;
        this.role = "USER";
    }
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
