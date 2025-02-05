package com.fisa.recycling_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Members")
public class Member {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int no;
    private String street;
    private String city;
    private int zip_code;  // Correction ici
    private String mail;
    private String password;
    private Role role;
    @DBRef
    private Group groupId;
}
