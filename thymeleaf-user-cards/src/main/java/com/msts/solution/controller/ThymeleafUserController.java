package com.msts.solution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.msts.solution.model.User;
import com.msts.solution.service.UserService;

@RestController
public class ThymeleafUserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/user-cards")
	public ModelAndView getAllUsers() throws Exception {
		List<User> users = userService.getAllUsers();
		ModelAndView view = new ModelAndView();
		if (users != null && !users.isEmpty()) {
			view.setViewName("user-cards");
			view.setStatus(HttpStatus.OK);
			view.addObject(users);
		} else {
			view.setStatus(HttpStatus.BAD_REQUEST);
		}
		return view;
	}

}
