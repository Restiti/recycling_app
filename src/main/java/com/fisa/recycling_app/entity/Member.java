package com.fisa.recycling_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Members")
public class Member {
    @Id
    private ObjectId idMember;
    private String firstName;
    private String lastName;
    private Address address;
    private String mail;
    @DBRef
    private ObjectId groupId; // Référence au groupe par ObjectId
}
