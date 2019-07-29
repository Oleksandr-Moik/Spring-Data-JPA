package com.devsmile.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsmile.springdata.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    User findByUserId(Integer id);
//    
//    List<User> findByFirstNameLike(String name);
//    
//    @Query("select coalesce(max(e.age) from user e")
//    Integer getMaxAge();
//    @Query("select coalesce(max(e.id) from user e")
//    Integer getMaxId();
    
}
