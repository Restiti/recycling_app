package com.fisa.recycling_app.controller;

import com.fisa.recycling_app.entity.Commande;
import com.fisa.recycling_app.entity.Group;
import com.fisa.recycling_app.entity.Material;
import com.fisa.recycling_app.entity.Member;
import com.fisa.recycling_app.repo.CommandeRepo;
import com.fisa.recycling_app.repo.GroupRepo;
import com.fisa.recycling_app.repo.MaterialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/commande")
public class CommandeController {
    @Autowired
    private CommandeRepo commandeRepo;
    @Autowired
    private MaterialRepo materialRepo;
    @GetMapping("/findAll")
    public List<Commande> getCommande(){

        return commandeRepo.findAll();
    }

    @PostMapping("/insertCommande")
    public Commande getCommande(@RequestBody Commande c){
        System.out.println(c);
        System.out.println("no commande: " + c.getId());
        return commandeRepo.save(c);
    }

    @DeleteMapping("/deleteCommande/{idCommande}")
    public ResponseEntity<?> deleteMember(@PathVariable String idCommande) {
        System.out.println("Je delete: " + idCommande);
        return commandeRepo.findById(idCommande)
                .map(commande -> {
                    commandeRepo.delete(commande);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/addMaterial/{idCommande}/{idMaterial}") // Utilisez @PutMapping au lieu de @DeleteMapping pour ajouter un manager
    public Commande addCommande(@PathVariable String idCommande, @PathVariable String idMaterial) {
        Commande commande = commandeRepo.findById(idCommande)
                .orElseThrow(() -> new RuntimeException("Commande non trouvé"));
        Material material = materialRepo.findById(idMaterial)
                .orElseThrow(() -> new RuntimeException("Material non trouvé"));

        // Initialise la liste des managers si elle est null
        if (commande.getListeMateriel() == null) {
            commande.setListeMateriel(new ArrayList<>());
        }
        commande.setPrixTotal(commande.getPrixTotal() + material.getPrix());

        commande.getListeMateriel().add(material);
        return commandeRepo.save(commande);

    }

    @DeleteMapping("/removeMaterial/{idCommande}/{idMaterial}")
    public Commande removeCommande(@PathVariable String idCommande, @PathVariable String idMaterial) {
        Commande commande = commandeRepo.findById(idCommande)
                .orElseThrow(() -> new RuntimeException("Commande non trouvé"));
        Material material = materialRepo.findById(idMaterial)
                .orElseThrow(() -> new RuntimeException("Material non trouvé"));

        List<Material> listeMateriel = commande.getListeMateriel();
        if (listeMateriel != null && listeMateriel.contains(material)) {
            commande.setPrixTotal(commande.getPrixTotal() - material.getPrix());
            listeMateriel.remove(material);
            commande.setListeMateriel(listeMateriel);
        }

        return commandeRepo.save(commande);
    }

}
