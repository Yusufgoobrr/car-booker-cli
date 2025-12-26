package com.yusuf.user;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserFakerDataAccsessService implements UserDAO {

    @Override
    public List<User> getAllUsers() {
        Faker faker = new Faker();
       List<User> users = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            User user = new User(UUID.randomUUID(), faker.name().firstName(), faker.name().lastName());
          users.add(user);
        }
        return users;
    }
}
