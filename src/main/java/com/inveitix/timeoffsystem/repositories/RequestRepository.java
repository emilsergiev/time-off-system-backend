package com.inveitix.timeoffsystem.repositories;

import org.springframework.data.repository.CrudRepository;

import com.inveitix.timeoffsystem.entities.Request;

public interface RequestRepository extends CrudRepository<Request, Long> {

}