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
    private ObjectId noserie;

    String marque;
    String modele;
    MaterialType materialType;
    double prix;
    private ObjectId stockageId; // Référence à Group par ObjectId
}
