package com.stackroute.hackathon.service;

import java.util.ArrayList;
import com.stackroute.hackathon.exception.UserAlreadyExistsException;
import com.stackroute.hackathon.exception.UserNotFoundException;
import com.stackroute.hackathon.model.UserModel;

interface RestHackathonService {
	void addUser(UserModel user) throws UserAlreadyExistsException;
	ArrayList<UserModel> read();
	UserModel readById(String Id) throws UserNotFoundException;
	void update(UserModel updateData) throws UserNotFoundException;
	void deleteById(String userId) throws UserNotFoundException;
}