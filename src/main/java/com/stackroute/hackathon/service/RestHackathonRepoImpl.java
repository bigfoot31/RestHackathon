package com.stackroute.hackathon.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.hackathon.exception.UserAlreadyExistsException;
import com.stackroute.hackathon.exception.UserNotFoundException;
import com.stackroute.hackathon.model.UserModel;
import com.stackroute.hackathon.repository.RestHackathonCRUDRepository;



@Service
public class RestHackathonRepoImpl implements RestHackathonService {

	@Autowired
	private RestHackathonCRUDRepository repoObject;

	@Override
	public ArrayList<UserModel> read() {
		return (ArrayList<UserModel>) this.repoObject.findAll();
	}

	@Override
	public UserModel readById(String userId) throws UserNotFoundException {
		if (repoObject.exists(userId)) {
			return repoObject.findOne(userId);
		} else {
			throw new UserNotFoundException("User with Id " + userId + " does not exist");
		}
	}

	@Override
	public void update(UserModel updateData) throws UserNotFoundException {
		
		if (repoObject.exists(updateData.getId())) {
			repoObject.save(updateData);
		} else {
			throw new UserNotFoundException("User with Id " + updateData.getId() + " does not exist");
		}
	}

	@Override
	public void addUser(UserModel user) throws UserAlreadyExistsException {
		if (repoObject.exists(user.getId())) {
			throw new UserAlreadyExistsException("User with Id " + user.getId() + " already exist");
		} else {
			repoObject.save(user);
		}
	}


	@Override
	public void deleteById(String userId) throws UserNotFoundException {
		if (repoObject.exists(userId)) {
			repoObject.delete(userId);
		} else {
			throw new UserNotFoundException("User with Id " + userId + " does not exist");
		}
		
	}
}
