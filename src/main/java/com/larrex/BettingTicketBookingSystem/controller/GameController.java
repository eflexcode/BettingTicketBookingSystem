package com.larrex.BettingTicketBookingSystem.controller;

import com.larrex.BettingTicketBookingSystem.model.Game;
import com.larrex.BettingTicketBookingSystem.model.Status;
import com.larrex.BettingTicketBookingSystem.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("game/")
public class GameController {

    @Autowired
    GameRepository repository;

    @PostMapping("create")
    public ResponseEntity<Status> crateGame(@RequestBody Game game) {

        game.setGameId(UUID.randomUUID().toString());
        repository.save(game);

        return ResponseEntity.ok(Status.SUCCESS);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Game> getGameWithId(@PathVariable String id) {

        Game game = repository.findGameById(id);

        if (game != null) {
            return ResponseEntity.ok(game);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No game found with id " + id);
        }

    }

    @GetMapping("get_by_category_id")
    public ResponseEntity<List<Game>> getAllGamesByCategory(@RequestParam(name = "categoryId") String categoryId) {

        return ResponseEntity.ok(repository.findGameByCategory(categoryId));

    }

    @PutMapping("update/{id}")
    public ResponseEntity<Status> updateGame(@PathVariable String id, @RequestBody Game game) {

        repository.save(game);

        return ResponseEntity.ok(Status.SUCCESS);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Status> deleteGame(@PathVariable String id){
        repository.deleteById(id);

        return ResponseEntity.ok(Status.SUCCESS);
    }

}
