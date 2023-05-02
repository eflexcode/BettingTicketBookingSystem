package com.larrex.BettingTicketBookingSystem.controller;

import com.larrex.BettingTicketBookingSystem.model.SportCategory;
import com.larrex.BettingTicketBookingSystem.model.Status;
import com.larrex.BettingTicketBookingSystem.repository.SportCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sport_category/")
public class SportCategoryController {

    @Autowired
    SportCategoryRepository repository;

    @PostMapping("create")
    public ResponseEntity<Status> createSportCategory(@RequestBody SportCategory sportCategory) {
        sportCategory.setId(UUID.randomUUID().toString());
        repository.save(sportCategory);
        return ResponseEntity.ok(Status.SUCCESS);
    }

    @GetMapping("get_all")
    public ResponseEntity<List<SportCategory>> getAllSportCategory() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<SportCategory> getSportCategoryWithId(@PathVariable String id) {

        SportCategory sportCategory = repository.getSingle(id);

        if (sportCategory != null)
            return ResponseEntity.ok(sportCategory);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sport category found with id " + id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Status> updateSportCategoryWithId(@PathVariable String id, @RequestBody SportCategory oldSportCategory) {

        SportCategory newSportCategory = repository.getSingle(id);
        newSportCategory.setCategoryName(oldSportCategory.getCategoryName());

        SportCategory justSavedSportCategory = repository.save(newSportCategory);

        if (justSavedSportCategory != null)
            return ResponseEntity.ok(Status.SUCCESS);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sport category found with id " + id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Status> deleteSportCategoryWithId(@PathVariable String id) {

        SportCategory sportCategory = repository.getSingle(id);
        if (sportCategory != null) {
            repository.delete(sportCategory);
            return ResponseEntity.ok(Status.SUCCESS);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sport category found with id " + id);
        }

    }

}
