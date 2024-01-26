package com.fisa.recycling_app.controller;

import com.fisa.recycling_app.entity.Group;
import com.fisa.recycling_app.entity.Member;
import com.fisa.recycling_app.repo.GroupRepo;
import com.fisa.recycling_app.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupRepo groupRepo;

    @GetMapping("/findAll")
    public List<Group> getGroup(){
        return groupRepo.findAll();
    }

    @PostMapping("/insertGroup")
    public Group getGroup(@RequestBody Group m){
        System.out.println(m);
        System.out.println("Nogroup: " + m.getNoGroup());
        return groupRepo.save(m);
    }

}
