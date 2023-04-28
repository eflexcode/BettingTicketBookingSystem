package com.larrex.BettingTicketBookingSystem.controller;

import com.larrex.BettingTicketBookingSystem.model.Status;
import com.larrex.BettingTicketBookingSystem.model.User;
import com.larrex.BettingTicketBookingSystem.repository.UserRepository;
import com.mongodb.client.model.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @PostMapping("create/")
    public Status createUser(@RequestBody User user) {
        String date = new Date(System.currentTimeMillis()).toString();
        user.setLastUpdateAt(date);
        user.setJoinedDateAt(date);

        userRepository.save(user);

        return Status.SUCCESS;
    }

    @GetMapping("get/{userId}")
    public User getUserWithId(@PathVariable String userId) {

        User user = userRepository.findByEmail(userId);

        return user;
    }

    @PutMapping("update/{userId}")
    public Status updateUserWithId(@PathVariable String userId, @RequestBody User oldUser) {

        User newUser = userRepository.findByEmail(userId);
        newUser.setName(oldUser.getName());
        newUser.setAge(oldUser.getAge());
        newUser.setLastUpdateAt(new Date(System.currentTimeMillis()).toString());

        userRepository.save(newUser);
        return Status.SUCCESS;
    }

    @DeleteMapping("delete/{userId}")
    public Status deleteUser(@PathVariable String userId) {

        User user = userRepository.findByEmail(userId);
        userRepository.delete(user);
        return Status.SUCCESS;
    }

}
