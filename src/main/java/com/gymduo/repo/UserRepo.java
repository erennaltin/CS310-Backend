package com.gymduo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.gymduo.model.User;


public interface UserRepo extends MongoRepository<User, String> {
	public User findByAppKey(String appKey);
}
