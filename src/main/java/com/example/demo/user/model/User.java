package com.example.demo.user.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.io.Serializable;
@Data
@Document
public class User implements Serializable {

    @Id
    private String id;

    @Field
    private String username;

    @Field
    private String password;
}
