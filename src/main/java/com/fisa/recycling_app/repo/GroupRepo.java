package com.fisa.recycling_app.repo;

import com.fisa.recycling_app.entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepo extends MongoRepository<Group, Integer> {
}
