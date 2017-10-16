package com.stackroute.hackathon.repository;

import org.springframework.data.repository.CrudRepository;

import com.stackroute.hackathon.model.UserModel;

public interface RestHackathonCRUDRepository extends CrudRepository<UserModel, String> {

}
