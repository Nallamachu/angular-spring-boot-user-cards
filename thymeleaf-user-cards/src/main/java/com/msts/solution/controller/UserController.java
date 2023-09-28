package com.msts.solution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msts.solution.model.User;
import com.msts.solution.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@CrossOrigin
	@GetMapping(path = "/user-list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsers() throws Exception {
		List<User> users = userService.getAllUsers();

		return (users != null && users.size() > 0) ? new ResponseEntity<List<User>>(users, HttpStatus.OK)
				: new ResponseEntity<String>("Failed to load user details", HttpStatus.BAD_REQUEST);
	}

}
