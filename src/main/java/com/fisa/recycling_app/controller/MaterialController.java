package com.fisa.recycling_app.controller;

import com.fisa.recycling_app.entity.Material;
import com.fisa.recycling_app.repo.MaterialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialRepo materialRepo;

    @GetMapping("/findAll")
    public List<Material> getmaterial(){
        return materialRepo.findAll();
    }

    @PostMapping("/insertmaterial")
    public Material getmaterial(@RequestBody Material m ){
        System.out.println(m);
        return materialRepo.save(m);
    }
}
