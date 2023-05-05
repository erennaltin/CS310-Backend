package com.gymduo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gymduo.enums.BMI;
import com.gymduo.enums.ExperienceLevel;
import com.gymduo.repo.ProgramExerciseRepo;

@Document("programs")
public class Program extends BaseModel {
	@Id
	private String id;
	
	private ExperienceLevel experienceLevel;
	private BMI bmiLevel;
	
	@Autowired ProgramExerciseRepo programExerciseRepo;
	
	public Program() {
		// TODO Auto-generated constructor stub
	}

	public Program(ExperienceLevel experienceLevel, BMI bmiLevel) {
		super();
		this.experienceLevel = experienceLevel;
		this.bmiLevel = bmiLevel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ExperienceLevel getLevel() {
		return experienceLevel;
	}

	public void setLevel(ExperienceLevel level) {
		this.experienceLevel = level;
	}

	public BMI getBmiLevel() {
		return bmiLevel;
	}

	public void setBmiLevel(BMI bmiLevel) {
		this.bmiLevel = bmiLevel;
	}
	
	
}
