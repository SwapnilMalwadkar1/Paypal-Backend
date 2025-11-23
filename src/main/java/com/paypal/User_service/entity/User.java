package com.paypal.User_service.entity;


import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
     private Long id ;

     private String name;


     @Column(unique = true)
     private  String email;

     private String password;



    public void SetId (Long id) {
        this.id = id;
      }
    public Long getId() {
      return id;

    }
  }



