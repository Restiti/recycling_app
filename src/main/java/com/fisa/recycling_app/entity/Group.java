package com.fisa.recycling_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Groups")
public class Group {
    @Id
    private String id;
    private String name;
    @DBRef
    private List<Member> managers;
    private int no;
    private String street;
    private String city;
    private int ZIP_code;
}
