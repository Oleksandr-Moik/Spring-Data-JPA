package com.devsmile.springdata.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devsmile.springdata.dao.address.AddressRepository;
import com.devsmile.springdata.dao.user.UserRepository;
import com.devsmile.springdata.model.address.Address;
import com.devsmile.springdata.model.user.User;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    private static final String[] FirstNames = new String[] { "Smith", "Jones", "John", "Robert", "Thomas", "Mark" };
    private static final String[] LastNames = new String[] { "Adams", "Perez", "Morgan", "Evans", "Bailey", "Carter" };

    private static final String[] Countries = new String[] { "Ukraine", "USA", "England", "France" };
    private static final String[] Cities = new String[] { "City-milion", "Town", "Village", "Country" };

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String homeMap(HttpServletRequest request) {
        String html = "";
        html += "<h1>Main page</h1>";
        html += String.format("<h3><a href=\"%suser\">%s</a></h3>", request.getRequestURL(), "Show all users");
        html += String.format("<h3><a href=\"%saddress\">%s</a></h3>", request.getRequestURL(), "Show all adresses");
        return html;
    }

    ///////////////////////////////////////////////////////////////
    /** USER **/
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
        user.setFirstName(FirstNames[new Random().nextInt(FirstNames.length)]);
        user.setLastName(LastNames[new Random().nextInt(LastNames.length)]);
        user.setAge(new Random().nextInt(20) + 10);
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

    ///////////////////////////////////////////////////////////////
    /** ADDRESS **/

    @RequestMapping(value = "/address", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllAdresses() {
        List<Address> list = addressRepository.findAll();
        String result = "";
        for (Address address : list) {
            result += address.toString();
        }
        return result;
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAddressById(@PathVariable("id") Integer id) {
        Optional<Address> optional = addressRepository.findById(id);

        if (optional.isPresent())
            return addressRepository.findById(id).get().toString();
        else
            return String.format("Address with id %d are not finded.", id);
    }

    @ResponseBody
    @RequestMapping(value = "/address", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertAddress(@RequestBody Address address) {
        address.setCity(Cities[new Random().nextInt(Cities.length)]);
        address.setCountry(Countries[new Random().nextInt(Countries.length)]);
        addressRepository.saveAndFlush(address);
        return "Saved: " + address.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateAddress(@RequestBody Address address, @PathVariable("id") Integer id) {
        Optional<Address> optional = addressRepository.findById(id);

        if (optional.isPresent()) {
            String result = "Address " + id + " chenged<br>";
            address.setId(id);
            result += "Data before: " + addressRepository.findById(id).get().toString() + "<br>";
            addressRepository.save(address);
            result += "Data after: " + address.toString() + "<br>";
            return result;
        } else
            return String.format("Address with id %d are not finded.", id);
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteAddress(@PathVariable("id") Integer id) {
        Optional<Address> optional = addressRepository.findById(id);

        if (optional.isPresent()) {
            String result = "Deleted: " + addressRepository.findById(id).get().toString();
            addressRepository.deleteById(id);
            return result;
        } else
            return String.format("Address with id %d are not finded.", id);
    }
}