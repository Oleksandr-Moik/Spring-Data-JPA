package com.devsmile.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.devsmile.dao.UserRepository;
import com.devsmile.model.entity.UserEntity;
import com.devsmile.model.entity.UserEntity.UserBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MainController { // LastName 2 //

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Integer id) throws Exception{
        if (userRepository.findById(id).isPresent()) {
            
            HttpHeaders httpHeaders= new HttpHeaders();
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> httpEntity= new HttpEntity<String>("parameters", httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            
            ResponseEntity<UserEntity> response = restTemplate.exchange("http://Age:8080/user/"+id, HttpMethod.GET, httpEntity, UserEntity.class);
            
            UserEntity user = response.getBody().builder().lastName(userRepository.findById(id).get().getLastName()).build();
            
            log.info("Service GET 2 lastName: {}",user.toString());
            
            return ResponseEntity.ok(user);
        } else {
            log.error("Service GET 2 lastName: user with id={} not found.",id);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = { "text/plain", "application/json" })
    public ResponseEntity<UserEntity> insertUser(@RequestBody UserEntity user) {
                
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserEntity> response = restTemplate.postForEntity("http://Age:8080/user/", user, UserEntity.class);
        
        UserEntity newUser = response.getBody();
        newUser.setLastName(user.getLastName());
        
        log.info("Service POST 2 lastName: {}",newUser.toString());
        
        userRepository.save(newUser);
        
        return ResponseEntity.ok(newUser);
    }
}