package com.larrex.BettingTicketBookingSystem.repository;

import com.larrex.BettingTicketBookingSystem.model.SportCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SportCategoryRepository extends MongoRepository<SportCategory,String > {

    @Query("{id: '?0'}")
    SportCategory getSingle(String id);

}
