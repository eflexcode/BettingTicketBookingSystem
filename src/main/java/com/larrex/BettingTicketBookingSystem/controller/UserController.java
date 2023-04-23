package com.larrex.BettingTicketBookingSystem.controller;

import com.larrex.BettingTicketBookingSystem.model.User;
import com.larrex.BettingTicketBookingSystem.repository.UserRepository;
import com.mongodb.client.model.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @PostMapping("/create/")
    public String createUser(@RequestBody User user){

       User newUse = userRepository.save(user);

        

       return "yes connected";
    }

}
