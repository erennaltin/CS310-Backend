package com.gymduo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.gymduo.model.Exercise;


public interface ExerciseRepo extends MongoRepository<Exercise, String> {
	public List<Exercise> findByName(String name);
}
