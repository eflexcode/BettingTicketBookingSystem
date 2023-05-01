package com.larrex.BettingTicketBookingSystem.controller;

import com.larrex.BettingTicketBookingSystem.model.SportCategory;
import com.larrex.BettingTicketBookingSystem.model.Status;
import com.larrex.BettingTicketBookingSystem.repository.SportCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("update/{id}")
    public ResponseEntity<Status> updateSPortCategoryWithId(@PathVariable String id, @RequestBody SportCategory oldSportCategory) {

        SportCategory newSportCategory = repository.getSingle(id);
        newSportCategory.setCategoryName(oldSportCategory.getCategoryName());

        repository.save(newSportCategory);

        return ResponseEntity.ok(Status.SUCCESS);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Status> deleteSportCategoryWithId(@PathVariable String id) {

        SportCategory sportCategory = repository.getSingle(id);

        repository.delete(sportCategory);
        return ResponseEntity.ok(Status.SUCCESS);
    }

}
