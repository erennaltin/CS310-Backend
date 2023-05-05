package com.gymduo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gymduo.model.ExerciseRecord;



public interface ExerciseRecordRepo extends MongoRepository<ExerciseRecord, String> {

}
