package com.gymduo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymduo.model.Exercise;
import com.gymduo.model.ExerciseRecord;
import com.gymduo.model.User;
import com.gymduo.model.requestmodels.CreateExerciseRecordRequestModel;
import com.gymduo.model.requestmodels.CreateExerciseRequestModel;
import com.gymduo.model.requestmodels.DeleteExerciseRequestModel;
import com.gymduo.model.requestmodels.UpdateExerciseRequestModel;
import com.gymduo.model.viewmodels.ExerciseViewModel;
import com.gymduo.payload.Data;
import com.gymduo.payload.Message;
import com.gymduo.repo.ExerciseRecordRepo;
import com.gymduo.repo.ExerciseRepo;
import com.gymduo.repo.UserRepo;

@RestController
@RequestMapping("/Exercise")
public class ExerciseController {

	@Autowired ExerciseRepo exerciseRepo;
	@Autowired ExerciseRecordRepo exerciseRecordRepo;
	@Autowired UserRepo userRepo;
	
	@PostMapping("/Create")
	public Message createExercise(@RequestBody CreateExerciseRequestModel model)
	{
		boolean isNewExercise = exerciseRepo.findByName(model.name).isEmpty();
		
		if (isNewExercise)
		{
			Exercise newExercise = new Exercise(model.name, model.description, model.photoURL, model.videoURL);
			Exercise newlyInsertedExercise = exerciseRepo.insert(newExercise);
			if (newlyInsertedExercise == null)
			{
				Message msg = new Message("The exercise could not be created. Please try again", 503);
				return msg;
			}
			else
			{
				Message msg = new Message("The exercise is succesfully created!", 200);
				return msg;
			}
			
		}
		else
		{
			Message msg = new Message("The exercise is already created!", 200);
			return msg;
		}
	}

	@PostMapping("/Update")
	public Message updateExercise(@RequestBody UpdateExerciseRequestModel model)
	{
		boolean isExerciseFound = exerciseRepo.findByName(model.name).isEmpty();
		
		if (isExerciseFound)
		{
			Exercise exerciseFound = exerciseRepo.findByName(model.name).get(0);

			if (model.name != null)
			{
				exerciseFound.setName(model.name);
			}
				
			if (model.description != null)
			{
				exerciseFound.setDescription(model.description);
			}
				
			if (model.photoURL != null)
			{
				exerciseFound.setDescription(model.photoURL);
			}
			
			if (model.videoURL != null)
			{
				exerciseFound.setDescription(model.videoURL);
			}
			Message msg = new Message("The exercise is succesfully updated!", 200);
			return msg;
				
		}
		
		else
		{
			Message msg = new Message("The exercise could not be found!", 404);
			return msg;
		}
	}
	
	@PostMapping("/Delete")
	public Message deleteExercise(@RequestBody DeleteExerciseRequestModel model) 
	{
		
		boolean isExerciseFound = exerciseRepo.findByName(model.name).isEmpty();
		if (!isExerciseFound)
		{
			Message msg = new Message("Exercise could not found!", 404);
			return msg;
		}
		
		Exercise exerciseFound = exerciseRepo.findByName(model.name).get(0);
		
		exerciseRepo.delete(exerciseFound);
		
		Message msg = new Message("Exercise succesfully deleted!", 200);
		return msg;
	}

	@GetMapping("/GetExercise/{id}")
	public Data<ExerciseViewModel> getExerciseDetail(@PathVariable("id") String id)
	{
		Optional<Exercise> exerciseFound = exerciseRepo.findById(id);
		
		if (exerciseFound.isEmpty())
		{
			Data<ExerciseViewModel> response = new Data<ExerciseViewModel>("Exercise could not be found!", 404, null);
			return response;
		}
		
		Exercise exercise = exerciseFound.get();
		
		ExerciseViewModel responseData = new ExerciseViewModel();
		responseData.name = exercise.getName();
		responseData.description = exercise.getDescription();
		responseData.photoURL = exercise.getPhotoURL();
		responseData.videoURL = exercise.getVideoURL();
		
		Data<ExerciseViewModel> response = new Data<ExerciseViewModel>("Exercise information succesfully obtained", 200, responseData);
		return response;
	}

	@PostMapping("/AddExerciseRecord")
	public Message addExerciseRecord(@RequestBody CreateExerciseRecordRequestModel model)
	{
		User userFound = userRepo.findByAppKey(model.appKey);
		if (userFound == null)
		{
			Message msg = new Message("User could not be found.", 404);
			return msg;
		}
		
		Optional<Exercise> exerciseFound = exerciseRepo.findById(model.exerciseId);
		
		if (exerciseFound.isEmpty())
		{
			Message msg = new Message("Exercise could not be found.", 404);
			return msg;
		}
		
		Exercise exercise = exerciseFound.get();
		
		ExerciseRecord newExerciseRecord = new ExerciseRecord(userFound, exercise, model.weight, model.maxRep, userFound.getWeight());
		ExerciseRecord newlyInsertedExerciseRecord = exerciseRecordRepo.insert(newExerciseRecord);
		if (newlyInsertedExerciseRecord == null)
		{
			Message msg = new Message("The exercise record could not be created. Please try again", 503);
			return msg;
		}
		else
		{
			Message msg = new Message("The exercise record is succesfully created!", 200);
			return msg;
		}
		
	}
}
