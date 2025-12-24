package com.yusuf.User;

import java.util.UUID;

public class UserService implements UserDAO {
    private static final User[] users;

    static {
        users = new User[]{
                new User(UUID.fromString("2ea85178-fada-4279-9d5e-eea627049fa2"), "Yusuf", "Kaya"),
                new User(UUID.fromString("576590ff-57a1-4df3-8430-79980eb42343"), "Nelson", "Amigos-code")
        };

    }

    @Override
    public User[] getAllUsers() {
        return users;
    }
}
