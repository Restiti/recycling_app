package com.fisa.recycling_app.dto;

import com.fisa.recycling_app.entity.Group;
import com.fisa.recycling_app.entity.Member;
import com.fisa.recycling_app.entity.Role;
import lombok.Data;

@Data
public class MemberDTO {
    private String id;
    private String firstName;
    private String lastName;
    private int no;
    private String street;
    private String city;
    private int zip_code;
    private String mail;
    private String password;
    private Role role;
    private String groupId;  // Stocker uniquement l'ID du groupe

    // 🔹 Méthode pour convertir DTO -> Entity
    public Member toEntity() {
        Member member = new Member();
        member.setId(this.id);
        member.setFirstName(this.firstName);
        member.setLastName(this.lastName);
        member.setNo(this.no);
        member.setStreet(this.street);
        member.setCity(this.city);
        member.setZip_code(this.zip_code);
        member.setMail(this.mail);
        member.setPassword(this.password);
        member.setRole(this.role);

        // Création de l'objet Group si l'ID est fourni
        if (this.groupId != null) {
            Group group = new Group();
            group.setId(this.groupId);
            member.setGroupId(group);
        }
        return member;
    }

    // 🔹 Méthode pour convertir Entity -> DTO
    public static MemberDTO fromEntity(Member member) {
        MemberDTO dto = new MemberDTO();
        dto.setId(member.getId());
        dto.setFirstName(member.getFirstName());
        dto.setLastName(member.getLastName());
        dto.setNo(member.getNo());
        dto.setStreet(member.getStreet());
        dto.setCity(member.getCity());
        dto.setZip_code(member.getZip_code());
        dto.setMail(member.getMail());
        dto.setPassword(member.getPassword());
        dto.setRole(member.getRole());

        if (member.getGroupId() != null) {
            dto.setGroupId(member.getGroupId().getId());
        }
        return dto;
    }
}
