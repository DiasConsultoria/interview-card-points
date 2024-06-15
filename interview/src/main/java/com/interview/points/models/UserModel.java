package com.interview.points.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    private Integer id;

    @Column(unique = true, nullable = false, name = "username")
    private String username;

    @Column(unique = true, nullable = false, name = "password")
    private String password;

    @Column(unique = true, nullable = false, name = "cpf")
    private String cpf;

    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @Column(unique = true, nullable = false, name = "tier")
    private Integer tier;

    @Column(unique = true, nullable = false, name = "points")
    private String points;
}
