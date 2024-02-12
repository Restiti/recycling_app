package com.fisa.recycling_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Commandes")
public class Commande {
    @Id
    private String id;

    @DBRef
    private Member membreClient; // Référence au Membre en tant que client

    @DBRef
    private Member membreActif; // Référence au Membre en tant qu'actif (simili-vendeur)

    private Date date;

    @DBRef
    private List<Material> listeMateriel; // Liste des matériaux commandés

    private double prixTotal;

    // Méthodes supplémentaires si nécessaire
}