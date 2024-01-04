package com.fisa.recycling_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Members")
public class Member {
    private String firstName;
    private String lastName;
    private Address address;
    private String mail;

}
