package com.devsmile.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsmile.springdata.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {}
