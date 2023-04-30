package com.larrex.BettingTicketBookingSystem.controller;

import com.larrex.BettingTicketBookingSystem.model.SportCategory;
import com.larrex.BettingTicketBookingSystem.model.Status;
import com.larrex.BettingTicketBookingSystem.repository.SportCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
