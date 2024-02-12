package com.fisa.recycling_app.repo;

import com.fisa.recycling_app.entity.Commande;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommandeRepo extends MongoRepository<Commande, String > {
}
