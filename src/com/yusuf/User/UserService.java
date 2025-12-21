package com.yusuf.User;

public class UserService {

    public void getAllUsers(User[] users) {
        for (User user : users) {
            System.out.println(user.getName() + " " + user.getSurname());
        }
    }
}
