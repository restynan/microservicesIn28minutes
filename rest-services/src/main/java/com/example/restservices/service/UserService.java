package com.example.restservices.service;

import com.example.restservices.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>();

    private static int count = 3;

    static {
        users.add(new User(1, "Resty", new Date()));
        users.add(new User(2, "Mike", new Date()));
        users.add(new User(3, "John", new Date()));

    }

    public List<User> findAll() {
        return users;

    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;

    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(count++);
        }
        users.add(user);
        return user;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;

    }

}


