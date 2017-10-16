package com.stackroute.hackathon.service;

import java.util.List;

import com.stackroute.hackathon.model.UserModel;

interface RestHackathonService {
	void create(UserModel newData);
	List<UserModel> read();
	UserModel readById(String Id);
	boolean update(UserModel updateData);
	boolean delete(UserModel deleteData);
	boolean deleteById(String userId);
}