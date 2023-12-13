package com.example.restapiwithmongodb.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.restapiwithmongodb.model.Hobbies;
import com.example.restapiwithmongodb.model.User;
import com.example.restapiwithmongodb.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		try {
			userService.findByEmail(user);
			return new ResponseEntity<>("User loggedIn!!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Invalid email or password", HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<?> deleteUser(@RequestParam("id") Integer id) {
		try {
			userService.findById(id);
			return new ResponseEntity<>("user Details Deleted!!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("user ID is invalid", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getUsers(){
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
		
	}
	@GetMapping("/getUser")
	public ResponseEntity<?> getUser(@RequestParam("username") String username){
		return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
		
	}
	
	@GetMapping("/pagination")
	public ResponseEntity<?> pagination(
			@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name="size" ,defaultValue = "5") int size){
		
		return new ResponseEntity<>(userService.pagination(page,size), HttpStatus.OK);
		
	}
	
	@PostMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody User user) throws Exception {
		try {
			userService.updateUser(user);
			return new ResponseEntity<>("user Details Updated!!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("user does not exist", HttpStatus.NOT_FOUND);
		}
	
	}
	
	@GetMapping("/getHobbies")
	public ResponseEntity<?> getUserHobbies(
			@RequestParam("id") Integer id){
		List<Hobbies> hobbies = userService.getUserHobbies(id);
		return new ResponseEntity<>(hobbies, HttpStatus.OK);
		
	}
	
	@PostMapping("/addHobbies")
	public ResponseEntity<?> addUserHobbies(Hobbies hobbies){
		 userService.addUserHobbies(hobbies);
		return new ResponseEntity<>("Hobbies added!!", HttpStatus.OK);
		
	}
	
}
