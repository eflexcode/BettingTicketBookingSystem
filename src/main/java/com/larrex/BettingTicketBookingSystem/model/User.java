package com.larrex.BettingTicketBookingSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document("User")
@Data
public class User {

    @Id
    private String email;
    private String name;
    private String joinedDateAt;
    private String lastUpdateAt;
    private int age;
    private boolean admin;
    private List<Ticket> tickets;

}
