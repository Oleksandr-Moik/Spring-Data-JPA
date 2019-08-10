
package com.devsmile.springdata.controller.user;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devsmile.springdata.dao.user.UserRepository;
import com.devsmile.springdata.model.user.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepos;
    

    private static final String[] FNAMES = new String[] { "Smith", "Allen", "Jones", "John", "Robert", "Thomas",
                    "Mark" };
    private static final String[] LNAMES = new String[] { "Hill", "Adams", "Perez", "Morgan", "Evans", "Bailey",
                    "Carter" };

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllUsers() {
        List<User> users = userRepos.findAll();
        String result = "";
        for (User user : users) {
            result += String.format("<p>%s</p>",user.toString());
        }
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserById(@PathVariable("id") Integer id) {
        Optional<User> student = userRepos.findById(id);

        if (student.isPresent())
            return userRepos.findById(id).get().toString();
        else
            return String.format("User with id %d are not finded.", id);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertUser(@RequestBody User user) {
        user.setFirstName(FNAMES[new Random().nextInt(FNAMES.length)]);
        user.setLastName(LNAMES[new Random().nextInt(LNAMES.length)]);
        user.setAge(new Random().nextInt(20) + 10);
        userRepos.saveAndFlush(user);
        return "Saved: " + user.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@RequestBody User user, @PathVariable("id") Integer id) {
        Optional<User> student = userRepos.findById(id);

        if (student.isPresent()) {
            String result = "User " + id + " chenged<br>";
            user.setId(id);
            result += "Data before: " + userRepos.findById(id).get().toString() + "<br>";
            userRepos.save(user);
            result += "Data after: " + user.toString() + "<br>";
            return result;
        } else
            return String.format("User with id %d are not finded.", id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUser(@PathVariable("id") Integer id) {
        Optional<User> student = userRepos.findById(id);

        if (student.isPresent()) {
            String result = "Deleted: " + userRepos.findById(id).get().toString();
            userRepos.deleteById(id);
            return result;
        } else
            return String.format("User with id %d are not finded.", id);
    }
}