package com.clonecoding.instagrambackend.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Roletype role;
}

