package com.fisa.recycling_app.controller;

import com.fisa.recycling_app.entity.Member;
import com.fisa.recycling_app.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class MemberController {
    @Autowired
    private MemberRepo memberRepo;

    @GetMapping("/findAll")
    public List<Member> getMember(){
        return memberRepo.findAll();
    }

    @PostMapping("/insertMember")
    public Member getMember(@RequestBody Member m){
        System.out.println("Insert member");
        System.out.println(m);
        return memberRepo.save(m);
    }

    @DeleteMapping("/deleteMember/{idMember}")
    public ResponseEntity<?> deleteMember(@PathVariable String idMember) {
        System.out.println("Je delete: " + idMember);
        return memberRepo.findById(idMember)
                .map(member -> {
                    memberRepo.delete(member);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        memberRepo.deleteAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable String id, @RequestBody Member memberDetails) {
        Optional<Member> memberOptional = memberRepo.findById(id);
        System.out.println(memberDetails.getFirstName());
        if (memberOptional.isPresent()) {
            Member memberToUpdate = memberOptional.get();

            // Mettre à jour les champs nécessaires de memberToUpdate avec ceux de memberDetails
            memberToUpdate.setFirstName(memberDetails.getFirstName());
            memberToUpdate.setLastName(memberDetails.getLastName());
            memberToUpdate.setMail(memberDetails.getMail());
            memberToUpdate.setZIP_code(memberDetails.getZIP_code());
            memberToUpdate.setNo(memberDetails.getNo());
            memberToUpdate.setStreet(memberDetails.getStreet());
            memberToUpdate.setCity(memberDetails.getCity());
            memberToUpdate.setRole(memberDetails.getRole());
            // Assurez-vous d'ajouter ici la logique pour mettre à jour tous les champs nécessaires, y compris les champs imbriqués comme l'adresse

            Member updatedMember = memberRepo.save(memberToUpdate); // Sauvegarde les modifications
            return ResponseEntity.ok(updatedMember);
        } else {
            return ResponseEntity.notFound().build(); // Renvoie un 404 si le membre n'est pas trouvé
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Member loginDetails) {
        System.out.println("Login");
        System.out.println(loginDetails);
        Member m = memberRepo.findByMail(loginDetails.getMail());
        if (m != null && m.getPassword().equals(loginDetails.getPassword())) {
            System.out.println("Connexion OK");
            // Connecter l'utilisateur (pour le moment, juste retourner l'utilisateur)
            return ResponseEntity.ok(m);
        }
        System.out.println("Error connexion");
        return ResponseEntity.badRequest().body("Invalid username or password");
    }


}
