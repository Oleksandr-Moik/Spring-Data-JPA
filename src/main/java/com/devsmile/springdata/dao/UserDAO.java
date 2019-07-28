package com.devsmile.springdata.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.devsmile.springdata.model.User;

@Repository
public class UserDAO {

    private static final Map<Integer, User> userMap = new HashMap<Integer, User>();
    private static Integer lastUsedUserId;
    
    static {
        initEmps();
    }
 
    private static void initEmps() {
        User user1 = new User(1, "Smith", "Clerk",23);
        User user2 = new User(2, "Allen", "Salesman",21);
        User user3 = new User(3, "Jones", "Manager",30);
 
        userMap.put(user1.getId(), user1);
        userMap.put(user2.getId(), user2);
        userMap.put(user3.getId(), user3);
        lastUsedUserId=3;
    }
 
    public User getUser(Integer id) {
        return userMap.get(id);
    }
 
    public User addUser(User user) {
        user.setId(++lastUsedUserId);
        userMap.put(user.getId(), user);
        return user;
    }
 
    public User updateUser(Integer id, User user) {
        userMap.put(id, user);
        return user;
    }
 
    public void deleteUser(Integer id) {
        userMap.remove(id);
    }
 
    public List<User> getAllUsers() {
        Collection<User> c = userMap.values();
        List<User> list = new ArrayList<User>();
        list.addAll(c);
        return list;
    }
    
}