package com.larrex.BettingTicketBookingSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Game")
@Data
public class Game {

    @Id
    private String gameId;
    private String startDate;
    private String endDate;
    private boolean liveGame;

    private SportCategory sportCategory;
}
