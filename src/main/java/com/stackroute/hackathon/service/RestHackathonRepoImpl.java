package com.stackroute.hackathon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.hackathon.model.UserModel;
import com.stackroute.hackathon.repository.RestHackathonCRUDRepository;

@Service
public class RestHackathonRepoImpl implements RestHackathonService {

	@Autowired
	private RestHackathonCRUDRepository repoObject;

	@Override
	public void create(UserModel newData) {
		this.repoObject.save(newData);
	}

	@Override
	public List<UserModel> read() {
		return (List<UserModel>) this.repoObject.findAll();
	}

	@Override
	public UserModel readById(String Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(UserModel updateData) {
		if(repoObject.findOne(updateData.getId()) != null) {
			this.repoObject.delete(updateData.getId());
			this.repoObject.save(updateData);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(UserModel deleteData) {
		if(repoObject.findOne(deleteData.getId()) != null) {
			this.repoObject.delete(deleteData.getId());
			return true;
		} else {
			return false;
		}
	}
}
