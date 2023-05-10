package com.gymduo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gymduo.model.Exercise;
import com.gymduo.model.ExerciseRecord;
import com.gymduo.model.User;



public interface ExerciseRecordRepo extends MongoRepository<ExerciseRecord, String> {
	List<ExerciseRecord> findByExerciseAndUser(Exercise exercise, User user);
}
