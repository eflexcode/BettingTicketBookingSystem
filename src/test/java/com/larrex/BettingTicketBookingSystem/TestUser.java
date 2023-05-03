package com.larrex.BettingTicketBookingSystem;

import com.larrex.BettingTicketBookingSystem.model.SportCategory;
import com.larrex.BettingTicketBookingSystem.model.User;
import com.larrex.BettingTicketBookingSystem.repository.SportCategoryRepository;
import com.larrex.BettingTicketBookingSystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TestUser {

    @Mock
    private UserRepository repository;

    @Mock
    private SportCategoryRepository categoryRepository;

    @Test
    void testGetUserWithId() {

        User user = new User();
        user.setName("Eze");

        Mockito.when(repository.findByEmail("ee")).thenReturn(user);
        String userName = repository.findByEmail("ee").getName();
        assertEquals(userName, "Eze");

    }

    @Test
    void testCreateCategory() {

        SportCategory category = new SportCategory();
        category.setCategoryName("Car race");
        category.setId(UUID.randomUUID().toString());

        Mockito.when(categoryRepository.save(category)).thenReturn(category);

        SportCategory savedCategory = categoryRepository.save(category);
        System.out.println(savedCategory);
        assertNotNull(savedCategory);
    }

}
