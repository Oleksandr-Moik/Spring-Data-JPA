package com.devsmile.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devsmile.springdata.model.User;
import com.devsmile.springdata.repository.UserRepository;

@Component
public class UserService {

    @Autowired UserRepository repository;
    
    public void add(User user) {
//        repository.save(toEntity(user));
    }
    
    public void delete(Integer id) {
        repository.deleteById(id);
    }
    
//    public List<User> getUsers(){
//        return 
//    }
}
