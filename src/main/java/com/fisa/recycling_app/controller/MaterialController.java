package com.fisa.recycling_app.controller;

import com.fisa.recycling_app.entity.Group;
import com.fisa.recycling_app.entity.Material;
import com.fisa.recycling_app.entity.MaterialType;
import com.fisa.recycling_app.repo.GroupRepo;
import com.fisa.recycling_app.repo.MaterialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialRepo materialRepo;
    @Autowired
    private GroupRepo groupRepo;
    @GetMapping("/findAll")
    public List<Material> getmaterial(){
        return materialRepo.findAll();
    }

    @PostMapping("/insertMaterial")
    public Material getmaterial(@RequestBody Material m ){
        System.out.println(m);
        return materialRepo.save(m);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> updateMaterial(@PathVariable String id, @RequestBody Material materialDetails) {
        System.out.println("ID: " + id);
        Optional<Material> MaterialOptional = materialRepo.findById(id);
        System.out.println(materialDetails.getModele());
        if (MaterialOptional.isPresent()) {
            Material materialToUpdate = MaterialOptional.get();

            // Mettre à jour les champs nécessaires de materialToUpdate avec ceux de materialDetails
            materialToUpdate.setModele(materialDetails.getModele());
            materialToUpdate.setMarque(materialDetails.getMarque());
            materialToUpdate.setPrix(materialDetails.getPrix());
            materialToUpdate.setMaterialType(materialDetails.getMaterialType());
            // Gestion de la référence stockage
            if(materialDetails.getStockage() != null && materialDetails.getStockage().getId() != null) {
                Optional<Group> stockageGroup = groupRepo.findById(materialDetails.getStockage().getId());
                if(stockageGroup.isPresent()) {
                    materialToUpdate.setStockage(stockageGroup.get());
                } else {
                    // Gérer le cas où le groupe n'existe pas
                }
            } else {
                materialToUpdate.setStockage(null);
            }            // Assurez-vous d'ajouter ici la logique pour mettre à jour tous les champs nécessaires, y compris les champs imbriqués comme l'adresse

            Material updatedMaterial = materialRepo.save(materialToUpdate); // Sauvegarde les modifications
            return ResponseEntity.ok(updatedMaterial);
        } else {
            return ResponseEntity.notFound().build(); // Renvoie un 404 si le membre n'est pas trouvé
        }
    }

    @DeleteMapping("/deleteMaterial/{idMaterial}")
    public ResponseEntity<?> deleteMember(@PathVariable String idMaterial) {
        System.out.println("Je delete: " + idMaterial);
        return materialRepo.findById(idMaterial)
                .map(material -> {
                    materialRepo.delete(material);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
