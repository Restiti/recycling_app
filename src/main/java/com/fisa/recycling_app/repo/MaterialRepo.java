package com.fisa.recycling_app.repo;

import com.fisa.recycling_app.entity.Material;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MaterialRepo extends MongoRepository<Material, String> {
}
