package com.assignment.login.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.assignment.login.model.Users;

public interface UserRepository extends MongoRepository<Users, Long>{

}
