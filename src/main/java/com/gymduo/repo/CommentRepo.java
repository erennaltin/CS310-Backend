package com.gymduo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gymduo.model.Comment;
import com.gymduo.model.Topic;
import com.gymduo.model.User;

public interface CommentRepo extends MongoRepository<Comment, String> {
	public List<Comment> findByUser(User user);
	public List<Comment> findByTopic(Topic topic);
}
