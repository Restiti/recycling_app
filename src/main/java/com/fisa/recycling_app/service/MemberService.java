package com.fisa.recycling_app.service;

import com.fisa.recycling_app.dto.MemberDTO;
import com.fisa.recycling_app.entity.Member;
import com.fisa.recycling_app.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private MemberRepo memberRepo;

    public List<MemberDTO> getAllMembers() {
        return memberRepo.findAll().stream()
                .map(MemberDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public MemberDTO insertMember(MemberDTO memberDTO) {
        Member savedMember = memberRepo.save(memberDTO.toEntity());
        return MemberDTO.fromEntity(savedMember);
    }

    public boolean deleteMember(String id) {
        if (memberRepo.existsById(id)) {
            memberRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public MemberDTO updateMember(String id, MemberDTO memberDTO) {
        Optional<Member> optionalMember = memberRepo.findById(id);
        if (optionalMember.isPresent()) {
            Member memberToUpdate = optionalMember.get();
            memberToUpdate.setFirstName(memberDTO.getFirstName());
            memberToUpdate.setLastName(memberDTO.getLastName());
            memberToUpdate.setMail(memberDTO.getMail());
            memberToUpdate.setZip_code(memberDTO.getZip_code());
            memberToUpdate.setNo(memberDTO.getNo());
            memberToUpdate.setStreet(memberDTO.getStreet());
            memberToUpdate.setCity(memberDTO.getCity());
            memberToUpdate.setRole(memberDTO.getRole());

            Member updatedMember = memberRepo.save(memberToUpdate);
            return MemberDTO.fromEntity(updatedMember);
        }
        return null;
    }

    public MemberDTO loginUser(String email, String password) {
        Member member = memberRepo.findByMail(email);
        if (member != null && member.getPassword().equals(password)) {
            return MemberDTO.fromEntity(member);
        }
        return null;
    }
}
