package com.larrex.BettingTicketBookingSystem.repository;

import com.larrex.BettingTicketBookingSystem.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {

    @Query("{gameId: '?0'}")
    Game findGameById(String id);

    @Query("{sportCategoryId: '?0'}")
    List<Game> findGameByCategory(String id);

//    List<Game> findGamesBySportType(String sportType);

}
