package com.devsmile.springdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devsmile.springdata.dao.UserDAO;
import com.devsmile.springdata.model.User;

@RestController
public class MainController {
    
    @Autowired
    private UserDAO userDAO;
    
    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to REST-Template Example.";
    }
    
    @RequestMapping(value = "/user",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getUsers(){
        List<User> list = userDAO.getAllUsers();
        return list;
    }
    @RequestMapping(value = "/user/{userID}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User getUser(@PathVariable("userID")Integer userID){
        return userDAO.getUser(userID);
    }
    
    @RequestMapping(value = "/user",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User addUser(@RequestBody User user) {
        //System.out.println("Creating user: "+user.getId());
        return userDAO.addUser(user);
    }
   
    @RequestMapping(value = "/user/{userID}",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User updateUser(@PathVariable("userID")Integer userID,@RequestBody User user) {
        //System.out.println("Editing user: "+user.getId());
        return userDAO.updateUser(userID, user);
    }

    @RequestMapping(value = "/user/{userID}",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private void deleteUser(@PathVariable("userID")Integer userID) {
        //System.out.println("Deleting user: "+userID);
        userDAO.deleteUser(userID);
    }
}