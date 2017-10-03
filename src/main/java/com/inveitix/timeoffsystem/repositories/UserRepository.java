package com.inveitix.timeoffsystem.repositories;

import org.springframework.data.repository.CrudRepository;

import com.inveitix.timeoffsystem.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

}