package com.stackroute.hackathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.hackathon.model.UserModel;
import com.stackroute.hackathon.service.RestHackathonRepoImpl;

@RestController
@RequestMapping("/hackathon/user")
@CrossOrigin(origins = "*")
public class RestHackathonController {
	@Autowired
	private RestHackathonRepoImpl _Service;
	
	//Get Request
	@GetMapping(produces="application/json")
	public ResponseEntity<List<UserModel>> getJson() {
		return new ResponseEntity<List<UserModel>>(this._Service.read(), HttpStatus.OK);
	}
	
	//Post Request
	@PostMapping(consumes="application/json")
	public ResponseEntity<String> postJson(RequestEntity<List<UserModel>> newListData) {
		for(UserModel g: newListData.getBody()) {
			this._Service.create(g);
		}
		
		return new ResponseEntity<String>("Data saved successfully", HttpStatus.CREATED);
	}
	
	//PUT Request
	@PutMapping(consumes="application/json")
	public ResponseEntity<String> putJson(RequestEntity<UserModel> updateData) {
		boolean isUpdate = this._Service.update(updateData.getBody());
		
		if(isUpdate) {
			return new ResponseEntity<String>("Data saved successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Data not found.", HttpStatus.BAD_REQUEST);
		}
	}
	
	//DELETE Request
	@DeleteMapping(consumes="application/json")
	public ResponseEntity<String> deleteJson(RequestEntity<List<UserModel>> deleteListData) {
		
		for( UserModel g:deleteListData.getBody()) {
			boolean isDeleted = this._Service.delete(g);
			
			if(!isDeleted) {
				return new ResponseEntity<String>("Data does not exist", HttpStatus.BAD_REQUEST);
			}
		}
		
		return new ResponseEntity<String>("Data deleted.", HttpStatus.OK);
	}
}
