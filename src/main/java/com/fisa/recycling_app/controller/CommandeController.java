package com.fisa.recycling_app.controller;

import com.fisa.recycling_app.entity.Commande;
import com.fisa.recycling_app.entity.Group;
import com.fisa.recycling_app.repo.CommandeRepo;
import com.fisa.recycling_app.repo.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/commande")
public class CommandeController {
    @Autowired
    private CommandeRepo commandeRepo;
    @GetMapping("/findAll")
    public List<Commande> getCommande(){

        return commandeRepo.findAll();
    }


}
