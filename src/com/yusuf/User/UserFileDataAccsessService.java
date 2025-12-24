package com.yusuf.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class UserFileDataAccsessService implements UserDAO {

    private static final String FILE_PATH = "src/com/yusuf/User/users.txt";

    @Override
    public User[] getAllUsers() {

        User[] users = new User[2];
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

                if (currentSize >= users.length) {
                    users = Arrays.copyOf(users, users.length + 2);
                }

                users[currentSize++] = user;
            }

        } catch (IOException e) {
            System.out.println("Failed to read users file");
        }

        return Arrays.copyOf(users, currentSize);
    }

}
