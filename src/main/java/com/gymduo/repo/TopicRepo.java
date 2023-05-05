package com.gymduo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gymduo.model.Topic;

public interface TopicRepo extends MongoRepository<Topic, String> {
	public Topic findByTitle(String title);
}