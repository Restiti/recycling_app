package com.fisa.recycling_app.repo;

import com.fisa.recycling_app.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepo extends MongoRepository<Member, String> {
    Member findByMail(String mail);

}
