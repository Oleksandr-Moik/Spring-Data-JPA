package com.devsmile.springdata.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsmile.springdata.model.User;
import com.devsmile.springdata.repository.UserRepository;

@RestController
public class MainController {
    
    @Autowired
    private UserRepository userRepository;
    
    private static final String[] FNAMES = new String[] { "Smith", "Allen", "Jones" };
    private static final String[] LNAMES = new String[] { "Clerk", "Salesman", "Manager" };
  
    @RequestMapping("/")
//    @ResponseBody
    public String home() {
        String html = "";
        html += "<ul>";
        html += " <li><a href='/testInsert'>Test Insert</a></li>";
        html += " <li><a href='/showAllUsers'>Show All Users</a></li>";
        html += " <li><a href='/user/1'>Show User 1</a></li>";
        html += "</ul>";
        return html;
    }
    
//    @ResponseBody
    @RequestMapping("/testInsert")
    public String testInsert() {
 
//        Integer empIdMax = userRepository.getMaxId();
//        Integer newId = empIdMax + 1;
//        
//        User user= new User();
//        
//        int random1 = new Random().nextInt(3);
//        int random2 = new Random().nextInt(3);
//        
//        String firstName = FNAMES[random1];
//        String lastName = LNAMES[random2];
// 
//        user.setId(newId);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setAge(new Random().nextInt(20)+15);
//        this.userRepository.save(user);
// 
//        return "Inserted: " + user.toString();
        return "Hello";
    }
    
    @RequestMapping("/showAllUsers")
//    @ResponseBody
    public String getUsers(){
        Iterable<User> users = userRepository.findAll();
        String html="";
        for(User user:users) {
            html+=user.toString()+"<br>";
        }
        return html;
    }
    
    @RequestMapping("/user/{id}")
//    @ResponseBody
    public User getUser(@PathVariable("id")Integer id){
        return userRepository.findById(id).get();
    }
}