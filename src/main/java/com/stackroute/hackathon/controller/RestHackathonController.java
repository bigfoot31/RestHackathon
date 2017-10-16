package com.stackroute.hackathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.hackathon.exception.UserAlreadyExistsException;
import com.stackroute.hackathon.exception.UserNotFoundException;
import com.stackroute.hackathon.model.UserModel;
import com.stackroute.hackathon.service.RestHackathonRepoImpl;


@RestController
@RequestMapping("/v1.0/hackathon")
@CrossOrigin(origins = "*")
public class RestHackathonController {
	@Autowired
	private RestHackathonRepoImpl _Service;
	
	//Get Request
	@GetMapping(value="/user",produces="application/json")
	public ResponseEntity<List<UserModel>> getJson() {
		return new ResponseEntity<List<UserModel>>(this._Service.read(), HttpStatus.OK);
	}
	
	@GetMapping(value="/user/{id}" ,produces="application/json")
	public ResponseEntity getJsonById(@PathVariable("id") String userId) {
		
		try {
			UserModel user = this._Service.readById(userId);
			return new ResponseEntity<UserModel>(user, HttpStatus.OK);
		} catch (UserNotFoundException exp) {
			return new ResponseEntity<String>(exp.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping(value="/user",consumes="application/json")
	public ResponseEntity<String> postJson(RequestEntity<UserModel> newData) {
		try {
			if((newData.getBody().getUsername()==null)||(newData.getBody().getEmailid()==null)) {
				return new ResponseEntity<String>("Username or EmailId cannot be EMPTY", HttpStatus.PARTIAL_CONTENT);
			}else {
			this._Service.addUser(newData.getBody());
			return new ResponseEntity<String>("User Added Successfully", HttpStatus.CREATED);
			}
		} catch (UserAlreadyExistsException exp) {
			return new ResponseEntity<String>(exp.getMessage(), HttpStatus.CONFLICT);

		}
	}
	
	//PUT Request
	@PutMapping(value="/user",consumes="application/json")
	public ResponseEntity<String> putJson(RequestEntity<UserModel> updateData) {
		try {
			this._Service.update(updateData.getBody());
			return new ResponseEntity<String>(" User Updated Successfully", HttpStatus.OK);
		} catch (UserNotFoundException exp) {
			return new ResponseEntity<String>(exp.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}
	
	
	//DELETE 
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteJsonById(@PathVariable("id") String userId) {
			
			try {
				this._Service.deleteById(userId);
				return new ResponseEntity<String>("User with Id "+userId+" Deleted Succesfully ", HttpStatus.OK);
			} catch (UserNotFoundException exp) {
				return new ResponseEntity<String>(exp.getMessage(), HttpStatus.BAD_REQUEST);
			}
	}
	
	/******************* Default Request Mapping *****************************/

	@RequestMapping()
	public ResponseEntity<String> defaultMap() {
		return new ResponseEntity<String>("Request Not Found, Please Enter Proper Url", HttpStatus.NOT_FOUND);
	}
}
