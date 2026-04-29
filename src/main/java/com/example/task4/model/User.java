package com.example.task4.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    public User(String login, String mail) {
        this.login = login;
        this.mail = mail;
    }

    public void setName(String name) {
        this.login = login;
    }

    public void setmail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", login='" + login + '\'' +
                ", mail='" + mail + '\'';
    }
}