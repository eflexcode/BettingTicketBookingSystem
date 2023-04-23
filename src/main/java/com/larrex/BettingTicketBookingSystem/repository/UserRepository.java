package com.larrex.BettingTicketBookingSystem.repository;

import com.larrex.BettingTicketBookingSystem.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    @Query("{email: '?0'}")
    User findByEmail(String email);

    public long count();

}
