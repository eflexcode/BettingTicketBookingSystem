package com.larrex.BettingTicketBookingSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("SportCategory")
@Data
public class SportCategory {

    @Id
    private String id;
    private String categoryName;

}
