package com.yusuf.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserFakerDataAccsessServiceTest {
    private UserFakerDataAccsessService userFakerDataAccessService;

    @BeforeEach
    void setUp() {
        userFakerDataAccessService = new UserFakerDataAccsessService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUsers() {
        // given
        int sizeExpected = 20;
        // when
        List<User> users = userFakerDataAccessService.getAllUsers();
        // then
        assertThat(users).hasSize(sizeExpected);

    }
}