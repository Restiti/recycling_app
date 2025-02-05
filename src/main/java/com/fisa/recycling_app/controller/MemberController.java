package com.fisa.recycling_app.controller;

import com.fisa.recycling_app.dto.MemberDTO;
import com.fisa.recycling_app.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/findAll")
    public List<MemberDTO> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping("/insertMember")
    public ResponseEntity<MemberDTO> insertMember(@RequestBody MemberDTO memberDTO) {
        return ResponseEntity.ok(memberService.insertMember(memberDTO));
    }

    @DeleteMapping("/deleteMember/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable String id) {
        return memberService.deleteMember(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable String id, @RequestBody MemberDTO memberDTO) {
        MemberDTO updatedMember = memberService.updateMember(id, memberDTO);
        return updatedMember != null ? ResponseEntity.ok(updatedMember) : ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody MemberDTO loginDetails) {
        MemberDTO user = memberService.loginUser(loginDetails.getMail(), loginDetails.getPassword());
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.badRequest().body("Invalid username or password");
    }
}
