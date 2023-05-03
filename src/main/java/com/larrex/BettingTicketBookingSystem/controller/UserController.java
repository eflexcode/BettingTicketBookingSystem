package com.larrex.BettingTicketBookingSystem.controller;

import com.larrex.BettingTicketBookingSystem.model.Status;
import com.larrex.BettingTicketBookingSystem.model.Ticket;
import com.larrex.BettingTicketBookingSystem.model.User;
import com.larrex.BettingTicketBookingSystem.repository.UserRepository;
import com.mongodb.client.model.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @PostMapping("create")
    public ResponseEntity<Status> createUser(@RequestBody User user) {
        String date = new Date(System.currentTimeMillis()).toString();
        user.setLastUpdateAt(date);
        user.setJoinedDateAt(date);

        userRepository.save(user);

        return ResponseEntity.ok(Status.SUCCESS);
    }

    @GetMapping("get/{userId}")
    public ResponseEntity<User> getUserWithId(@PathVariable String userId) {

        User user = userRepository.findByEmail(userId);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("update/{userId}")
    public ResponseEntity<Status> updateUserWithId(@PathVariable String userId, @RequestBody User oldUser) {

        User newUser = userRepository.findByEmail(userId);

        if (newUser != null) {

            newUser.setName(oldUser.getName());
            newUser.setAge(oldUser.getAge());
            newUser.setLastUpdateAt(new Date(System.currentTimeMillis()).toString());

            userRepository.save(newUser);
            return ResponseEntity.ok(Status.SUCCESS);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<Status> deleteUser(@PathVariable String userId) {

        User user = userRepository.findByEmail(userId);

        if (user != null) {
            userRepository.delete(user);
            return ResponseEntity.ok(Status.SUCCESS);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


    }

    @PostMapping("ticket/create/{id}")
    public ResponseEntity<Status> createTicket(@PathVariable String id, @RequestBody Ticket ticket) {

        User user = userRepository.findByEmail(id);

        if (user != null) {

            ticket.setTicketId(UUID.randomUUID().toString());
            ticket.setCreatedAt(new Date(System.currentTimeMillis()).toString());
            user.getTickets().add(ticket);

            userRepository.save(user);
            return ResponseEntity.ok(Status.SUCCESS);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("ticket/get_all/{id}")
    public ResponseEntity<List<Ticket>> getAllTicketWithUserId(@PathVariable String id) {
        User user = userRepository.findByEmail(id);
        if (user != null) {
            return ResponseEntity.ok(user.getTickets());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("ticket/get/{id}")
    public ResponseEntity<Ticket> getTicketWithUserId(@PathVariable String id, @RequestParam(value = "index") String index) {
        User user = userRepository.findByEmail(id);
        if (user != null) {

            return ResponseEntity.ok(user.getTickets().get(Integer.parseInt(index)));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
