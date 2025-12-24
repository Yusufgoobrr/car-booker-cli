package com.yusuf.User;

public class UserService {

    private final UserDAO userDAO = new UserFileDataAccsessService();

    public User[] getAllUsers() {
        return userDAO.getAllUsers();
    }
}
