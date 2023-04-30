package com.larrex.BettingTicketBookingSystem.repository;

import com.larrex.BettingTicketBookingSystem.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GameRepository extends MongoRepository<Game,String> {

    @Query("{gameId: '?0'}")
    Game findGameById(String id);
    Game findGameByCategory(String id);

//    List<Game> findGamesBySportType(String sportType);

}
