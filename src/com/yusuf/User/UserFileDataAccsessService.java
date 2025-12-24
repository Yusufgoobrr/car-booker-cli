package com.yusuf.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UserFileDataAccsessService implements UserDAO {

    private static final String FILE_PATH = "src/com/yusuf/User/users.txt";

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>(2);
        int currentSize = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                User user = new User(
                        UUID.fromString(parts[0]),
                        parts[1],
                        parts[2]
                );

                users.add(user);
            }

        } catch (IOException e) {
            System.out.println("Failed to read users file");
        }

        return users;
    }

}
