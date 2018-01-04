package com.neu.wham.controllers;
/*
This controller is responsible for user registration.
Follows 2 steps:
1   Registers Users.
2   Validate Them
Import Linraries from spring Framework and Project packages.
*/
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.wham.messages.EventPostResponse;
import com.neu.wham.messages.EventPostResponse.Status;
import com.neu.wham.model.Event;
import com.neu.wham.model.User;
import com.neu.wham.services.UserRegistrationService;
/**
This method simply registers the user into the database 
**/
@Controller
public class UserRegistrationController {
	@Autowired
	private UserRegistrationService registrationService;
	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public @ResponseBody User registerUser(@Valid @ModelAttribute User user, BindingResult result){
		if(result.hasErrors()){
			return null;
		}
		return registrationService.registerUser(user);
	}
	/**
This method checks if the email id and password fileds are not left empty or are not provided a null value user before registering.
If the conditionals are satisfied the user records are entered to the database 
**/
	@RequestMapping(value="/validateUser",method=RequestMethod.POST)
	public @ResponseBody User validateUser(@RequestParam("emailId") String emailId, @RequestParam("password") String password){
		if(emailId== null ||  password == null ||emailId.isEmpty() || password.isEmpty()){
			return null;
		}
		return registrationService.validateUser(emailId, password);
	}
	
}
