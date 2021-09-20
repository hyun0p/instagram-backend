package com.clonecoding.instagrambackend.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private Roletype role;
}

