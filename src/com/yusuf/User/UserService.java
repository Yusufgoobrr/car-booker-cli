package com.yusuf.User;

public class UserService {

    public void getAllUsers(User[] users) {
        for (User user : users) {
            System.out.println("UserId: " + user.getUserId() + "\n" +
                    "Name: " + user.getName() + "\n" + "Surname: "
                    + user.getSurname()+"\n");
        }
    }
}
