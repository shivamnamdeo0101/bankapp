package com.shivam.bankapp.domain;

import lombok.*;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class Account {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Double balance;

    @Column(name = "is_admin")
    private Boolean isAdmin = false;

    @Column(name = "is_blocked")
    private Boolean isBlocked = false;

    @Column(name = "is_active")
    private Boolean isActive = false;

}
