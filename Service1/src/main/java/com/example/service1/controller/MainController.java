package com.example.service1.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.service1.dao.UserRepository;
import com.example.service1.model.User;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;

//    private static final String[] FirstNames = new String[] { "Smith", "Jones", "John", "Robert", "Thomas", "Mark" };
//    private static final String[] LastNames = new String[] { "Adams", "Perez", "Morgan", "Evans", "Bailey", "Carter" };

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String homeMap(HttpServletRequest request) {
        String html = "";
        html += "<h1>Main page (service1)</h1>";
        html += String.format("<h3><a href=\"%suser\">%s</a></h3>", request.getRequestURL(), "Show all users");
        return html;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllUsers() {
        List<User> list = userRepository.findAll();
        String result = "";
        for (User user : list) {
            result += user.toString();
        }
        return result;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserById(@PathVariable("id") Integer id) {
        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent())
            return userRepository.findById(id).get().toString();
        else
            return String.format("User with id %d are not finded.", id);
    }

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertUser(@RequestBody User user) {
//        user.setFirstName(FirstNames[new Random().nextInt(FirstNames.length)]);
//        user.setLastName(LastNames[new Random().nextInt(LastNames.length)]);
//        user.setAge(new Random().nextInt(20) + 10);
        userRepository.saveAndFlush(user);
        return "Saved: " + user.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@RequestBody User user, @PathVariable("id") Integer id) {
        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()) {
            String result = "User " + id + " chenged<br>";
            user.setId(id);
            result += "Data before: " + userRepository.findById(id).get().toString() + "<br>";
            userRepository.save(user);
            result += "Data after: " + user.toString() + "<br>";
            return result;
        } else
            return String.format("User with id %d are not finded.", id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUser(@PathVariable("id") Integer id) {
        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()) {
            String result = "Deleted: " + userRepository.findById(id).get().toString();
            userRepository.deleteById(id);
            return result;
        } else
            return String.format("User with id %d are not finded.", id);
    }
}