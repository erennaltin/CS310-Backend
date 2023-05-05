package com.gymduo.repo;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gymduo.model.Program;
import com.gymduo.model.ProgramExercise;


public interface ProgramExerciseRepo extends MongoRepository<ProgramExercise, String> {
	public List<ProgramExercise> findByProgram(Program program);
}
