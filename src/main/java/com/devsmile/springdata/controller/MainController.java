package com.devsmile.springdata.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devsmile.springdata.model.User;
import com.devsmile.springdata.repository.UserRepository;

@RestController
public class MainController {
    
    @Autowired
    private UserRepository repository;
    
    private static final String[] FNAMES = new String[] { "Smith", "Allen", "Jones","John","Robert","Thomas","Mark" };
    private static final String[] LNAMES = new String[] { "Hill", "Adams", "Perez","Morgan","Evans","Bailey","Carter" };
  
    @RequestMapping("/")
    public String home() {
        String html = "Hello. It is root link of Spring";
        return html;
    }
    
    @RequestMapping(value = "/user",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllUsers(){
        Iterable<User> users = repository.findAll();
        String html="";
        for(User user:users) {
            html+=user.toString()+"<br>";
        }
        return html;
    }
    
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserById(@PathVariable("id")Integer id){
        return repository.findById(id).get().toString();
    }
    
    @ResponseBody
    @RequestMapping(value = "/user",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertUser(@RequestBody User user) {
        user.setFirstName(FNAMES[new Random().nextInt(FNAMES.length)]);
        user.setLastName(LNAMES[new Random().nextInt(LNAMES.length)]);
        user.setAge(new Random().nextInt(20)+10);
        repository.saveAndFlush(user);
        return "Saved: "+user.toString();
    }
    
    @ResponseBody
    @RequestMapping(value = "/user/{id}",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@RequestBody User user, @PathVariable("id")Integer id) {
        String result="User "+id+" chenged<br>";
        user.setId(id);
        result+="Data before: "+repository.findById(id).get().toString()+"<br>";
        repository.save(user);
        result+="Data after: "+user.toString()+"<br>";
        return result;
    }
    
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUser(@PathVariable("id")Integer id) {
        String result="Deleted: "+repository.findById(id).get().toString();
        repository.deleteById(id);
        return result;
    }
    
    
}