package com.yusuf.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserFileDataAccsessService implements UserDAO {

    private final String FILE_PATH;

    // ✅ default constructor (production use)
    public UserFileDataAccsessService() {
        this.FILE_PATH =
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource("users.txt")
                ).getPath();
    }

    // ✅ constructor for tests (temp file)
    public UserFileDataAccsessService(String filePath) {
        this.FILE_PATH = filePath;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

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
