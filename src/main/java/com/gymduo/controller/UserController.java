package com.gymduo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymduo.model.User;
import com.gymduo.model.requestmodels.RegisterUserRequestModel;
import com.gymduo.model.requestmodels.UpdateUserRequestModel;
import com.gymduo.payload.Message;
import com.gymduo.repo.UserRepo;

@RestController
@RequestMapping("/User")
public class UserController {

	@Autowired UserRepo userRepo;
	
	@GetMapping("/GetUsers")
	public List<User> getUsers()
	{
		List<User> users = userRepo.findAll();
		return users;
	}
	
	@PostMapping("/Register")
	public Message registerUser(@RequestBody RegisterUserRequestModel model)
	{
		String appKey = model.appKey;
		User alreadyInsertedUser = userRepo.findByAppKey(appKey);
		
		if (alreadyInsertedUser == null)
		{
			User newUser = new User(appKey);
			User newlyInsertedUser = userRepo.insert(newUser);
			if (newlyInsertedUser == null)
			{
				Message msg = new Message("The user could not be registered. Please try again", 503);
				return msg;
			}
			else
			{
				Message msg = new Message("The user is succesfully registered!", 200);
				return msg;
			}
			
		}
		else
		{
			Message msg = new Message("The user is already registered!", 200);
			return msg;
		}
	}
	
	@PostMapping("/UpdateUser")
	public Message updateUser(@RequestBody UpdateUserRequestModel model)
	{
		if (model.appKey != null)
		{
			User user = userRepo.findByAppKey(model.appKey);
			if (model.firstName != null)
			{
				user.setFirstName(model.firstName);
			}
			
			if (model.lastName != null)
			{
				user.setLastName(model.lastName);
			}
			
			if (model.weight != 0)
			{
				user.setWeight(model.weight);
			}
			
			if (model.height != 0)
			{
				user.setHeight(model.height);
			}
			
			if (model.experienceLevel != null)
			{
				user.setExperienceLevel(model.experienceLevel);
			}
			
			
			User updatedUser = userRepo.save(user);
			if (updatedUser == null)
			{
				Message msg = new Message("The user could not be updated. Please try again", 503);
				return msg;
			}
			else
			{
				Message msg = new Message("The user is succesfully updated!", 200);
				return msg;
			}	
		}
		else
		{
			Message msg = new Message("The App Key is not provided!", 404);
			return msg;
			
		}
	}

}
