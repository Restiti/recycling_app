package com.fisa.recycling_app.controller;

import com.fisa.recycling_app.entity.Group;
import com.fisa.recycling_app.entity.Member;
import com.fisa.recycling_app.repo.GroupRepo;
import com.fisa.recycling_app.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private MemberRepo memberRepo;
    @GetMapping("/findAll")
    public List<Group> getGroup(){
        return groupRepo.findAll();
    }

    @PostMapping("/insertGroup")
    public Group getGroup(@RequestBody Group m){
        System.out.println(m);
        System.out.println("Nogroup: " + m.getId());
        return groupRepo.save(m);
    }

    @PutMapping("/addManager/{idGroup}/{idMember}") // Utilisez @PutMapping au lieu de @DeleteMapping pour ajouter un manager
    public Group addManager(@PathVariable String idGroup, @PathVariable String idMember) {
        Member member = memberRepo.findById(idMember)
                .orElseThrow(() -> new RuntimeException("Membre non trouvé"));
        Group group = groupRepo.findById(idGroup)
                .orElseThrow(() -> new RuntimeException("Groupe non trouvé"));

        // Initialise la liste des managers si elle est null
        if (group.getManagers() == null) {
            group.setManagers(new ArrayList<>());
        }

        // Vérifie si le membre est déjà un manager du groupe
        boolean isAlreadyManager = group.getManagers().stream()
                .anyMatch(m -> m.getId().equals(member.getId()));

        // Ajoute le membre à la liste des managers s'il n'est pas déjà présent
        if (!isAlreadyManager) {
            group.getManagers().add(member);
            return groupRepo.save(group);
        } else {
            // Optionnel : renvoyer le groupe sans modifications si le membre est déjà manager
            return group;
        }
    }


    @DeleteMapping("/removeManager/{idGroup}/{idMember}")
    public Group removeManager(@PathVariable String idGroup, @PathVariable String idMember) {
        Member member = memberRepo.findById(idMember)
                .orElseThrow(() -> new RuntimeException("Membre non trouvé"));
        Group group = groupRepo.findById(idGroup)
                .orElseThrow(() -> new RuntimeException("Groupe non trouvé"));

        List<Member> managers = group.getManagers();
        if (managers != null && managers.contains(member)) {
            managers.remove(member);
            group.setManagers(managers);
        }

        return groupRepo.save(group);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> updateMember(@PathVariable String id, @RequestBody Group memberDetails) {
        Optional<Group> memberOptional = groupRepo.findById(id);
        System.out.println(memberDetails.getName());
        if (memberOptional.isPresent()) {
            Group groupToUpdate = memberOptional.get();

            // Mettre à jour les champs nécessaires de groupToUpdate avec ceux de memberDetails
            groupToUpdate.setName(memberDetails.getName());
            groupToUpdate.setCity(memberDetails.getCity());
            groupToUpdate.setNo(memberDetails.getNo());
            groupToUpdate.setStreet(memberDetails.getStreet());
            groupToUpdate.setZIP_code(memberDetails.getZIP_code());

            // Assurez-vous d'ajouter ici la logique pour mettre à jour tous les champs nécessaires, y compris les champs imbriqués comme l'adresse

            Group updatedGroup = groupRepo.save(groupToUpdate); // Sauvegarde les modifications
            return ResponseEntity.ok(updatedGroup);
        } else {
            return ResponseEntity.notFound().build(); // Renvoie un 404 si le membre n'est pas trouvé
        }
    }

    @DeleteMapping("/deleteGroup/{idGroup}")
    public ResponseEntity<?> deleteMember(@PathVariable String idGroup) {
        System.out.println("Je delete: " + idGroup);
        return groupRepo.findById(idGroup)
                .map(group -> {
                    groupRepo.delete(group);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
