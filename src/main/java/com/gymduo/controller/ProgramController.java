package com.gymduo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymduo.model.Exercise;
import com.gymduo.model.Program;
import com.gymduo.model.ProgramExercise;
import com.gymduo.model.User;
import com.gymduo.model.requestmodels.CreateProgramExerciseRequestModel;
import com.gymduo.model.requestmodels.CreateProgramRequestModel;
import com.gymduo.model.requestmodels.DeleteProgramExerciseRequestModel;
import com.gymduo.model.viewmodels.ProgramExerciseViewModel;
import com.gymduo.model.viewmodels.ProgramViewModel;
import com.gymduo.payload.Data;
import com.gymduo.payload.Message;
import com.gymduo.repo.ExerciseRepo;
import com.gymduo.repo.ProgramExerciseRepo;
import com.gymduo.repo.ProgramRepo;
import com.gymduo.repo.UserRepo;


@RestController
@RequestMapping("/Program")
public class ProgramController {
	@Autowired ProgramRepo programRepo;
	@Autowired UserRepo userRepo;
	@Autowired ProgramExerciseRepo programExerciseRepo;
	@Autowired ExerciseRepo exerciseRepo;
	
	@PostMapping("/Create")
	public Message createProgram(@RequestBody CreateProgramRequestModel model)
	{
		Program programFound = programRepo.findByBmiLevelAndExperienceLevel(model.bmiLevel, model.experienceLevel); 
			
		if (programFound != null)
		{
			Message msg = new Message("Program is already available!", 200);
			return msg;
		}
		else
		{
			Program newProgram = new Program(model.experienceLevel, model.bmiLevel);
			Program insertedProgram = programRepo.insert(newProgram);
			if (insertedProgram != null)
			{				
				Message msg = new Message("Program is succesfully created!", 200);
				return msg;
			}
			else
			{
				Message msg = new Message("There was an error, please try again!", 500);
				return msg;
			}
		}
	}

	@GetMapping("/GetProgram/{appKey}")
	public Data<ProgramViewModel> getProgram(@PathVariable("appKey") String appKey)
	{
		User userFound = userRepo.findByAppKey(appKey);
		if (userFound == null)
		{
			Data<ProgramViewModel> response = new Data<ProgramViewModel>("User could not found!", 404, null);
			return response;
		}
		
		
		Program programFound = programRepo.findByBmiLevelAndExperienceLevel(userFound.calculateBMI(), userFound.getExperienceLevel());
		if (programFound == null)
		{
			Data<ProgramViewModel> response = new Data<ProgramViewModel>("Program could not found!", 404, null);
			return response;
		}
		
		List<ProgramExerciseViewModel> programExercises = programExerciseRepo.findByProgram(programFound).stream()
													.map(temp -> {
														Exercise exercise = temp.getExercise();
														ProgramExerciseViewModel model = new ProgramExerciseViewModel();
														model.name = exercise.getName();
														model.description = exercise.getDescription();
														model.photoURL = exercise.getPhotoURL();
														model.videoURL = exercise.getVideoURL();
														model.reps = temp.getReps();
														model.sets = temp.getSets();
														return model;
													}).collect(Collectors.toList());
														
		
		ProgramViewModel responseData = new ProgramViewModel(userFound.calculateBMI(), userFound.getExperienceLevel(), programExercises);		
		
		Data<ProgramViewModel> response = new Data<ProgramViewModel>("Program information succesfully obtained", 200, responseData);
		return response;
	}

	@PostMapping("/AddExercise")
	public Message createProgramExercise(@RequestBody CreateProgramExerciseRequestModel model)
	{
		Program programFound = programRepo.findByBmiLevelAndExperienceLevel(model.bmiLevel, model.experienceLevel);
		
		if (programFound == null)
		{
			Message msg = new Message("Program could not be found. Please create the program first!", 404);
			return msg;
		}
		
		
		List<Exercise> exercisesFound = exerciseRepo.findByName(model.exerciseName);
		
		if (exercisesFound.isEmpty())
		{
			Message msg = new Message("Exercise could not be found. Please create the exercise first!", 404);
			return msg;
		}
		
		Exercise exerciseFound = exercisesFound.get(0);
		
		List<ProgramExercise> programExercises = programExerciseRepo.findByProgram(programFound);
		List<ProgramExercise> filteredExercises = new ArrayList<ProgramExercise>();
		
		for (ProgramExercise exercise : programExercises) {
		    if (exercise.getExercise().getId().equals(exerciseFound.getId())) {
		    	filteredExercises.add(exercise);
		    }
		}
		
		if (!filteredExercises.isEmpty())
		{
			Message msg = new Message("This program already includes that exercise!", 200);
			return msg;
		}
		else
		{
			ProgramExercise newProgramExercise = new ProgramExercise(programFound, exerciseFound, model.reps, model.sets);
			ProgramExercise insertedProgramExercise = programExerciseRepo.insert(newProgramExercise);
			
			if (insertedProgramExercise == null)
			{
				Message msg = new Message("A problem occurred, please try again!", 503);
				return msg;
			}
			else
			{
				Message msg = new Message("Exercise succesfully added to the program", 200);
				return msg;	
			}
		}
	}
	
	@PostMapping("/RemoveExercise")
	public Message removeProgramExercise(@RequestBody DeleteProgramExerciseRequestModel model)
	{
		Program programFound = programRepo.findByBmiLevelAndExperienceLevel(model.bmiLevel, model.experienceLevel);
		
		if (programFound == null)
		{
			Message msg = new Message("Program could not be found. Please create the program first!", 404);
			return msg;
		}
		
		
		List<Exercise> exercisesFound = exerciseRepo.findByName(model.exerciseName);
		
		if (exercisesFound.isEmpty())
		{
			Message msg = new Message("Exercise could not be found. Please create the exercise first!", 404);
			return msg;
		}
		
		Exercise exerciseFound = exercisesFound.get(0);
		
		List<ProgramExercise> programExercises = programExerciseRepo.findByProgram(programFound);
		List<ProgramExercise> filteredExercises = new ArrayList<ProgramExercise>();
		
		for (ProgramExercise exercise : programExercises) {
		    if (exercise.getExercise().getId().equals(exerciseFound.getId())) {
		    	filteredExercises.add(exercise);
		    }
		}
		
		if (filteredExercises.isEmpty())
		{
			Message msg = new Message("This program does not include that exercise!", 200);
			return msg;
		}
		
		ProgramExercise programExerciseFound = filteredExercises.get(0);
		
		programExerciseRepo.delete(programExerciseFound);
		
		Message msg = new Message("Exercise succesfully deleted from that program!", 200);
		return msg;
	}
	
}
