package com.yusuf.User;

public class UserService {
private final UserDAO userDAO = new UserDAO();

    public User[] getAllUsers() {
     return  userDAO.findAllUsers();
    }
}
