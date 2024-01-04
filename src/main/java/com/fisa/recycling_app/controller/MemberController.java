package com.fisa.recycling_app.controller;

import com.fisa.recycling_app.entity.Member;
import com.fisa.recycling_app.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return memberRepo.save(m);
    }


}
