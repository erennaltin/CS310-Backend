package com.gymduo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gymduo.enums.BMI;
import com.gymduo.enums.ExperienceLevel;
import com.gymduo.model.Program;


public interface ProgramRepo extends MongoRepository<Program, String> {
	public List<Program> findByBmiLevel(BMI bmiLevel);
	public List<Program> findByExperienceLevel(ExperienceLevel experienceLevel);
	public Program findByBmiLevelAndExperienceLevel(BMI bmiLevel, ExperienceLevel experienceLevel);

}
