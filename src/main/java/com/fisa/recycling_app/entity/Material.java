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
@Document(collection = "Materials")
public class Material {
    @Id
    private String id; // Renommé pour plus de clarté

    private String marque;

    private String modele;

    private MaterialType materialType;

    private double prix;

    @DBRef
    private Group stockage; // Utilisation de @DBRef pour référencer un Group
}
