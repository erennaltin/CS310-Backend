package com.gymduo.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymduo.model.Comment;
import com.gymduo.model.Topic;
import com.gymduo.model.User;
import com.gymduo.model.requestmodels.CreateCommentRequestModel;
import com.gymduo.model.requestmodels.CreateTopicRequestModel;
import com.gymduo.model.requestmodels.DeleteCommentRequestModel;
import com.gymduo.model.requestmodels.DeleteTopicRequestModel;
import com.gymduo.model.viewmodels.CommentViewModel;
import com.gymduo.model.viewmodels.TopicViewModel;
import com.gymduo.payload.Data;
import com.gymduo.payload.Message;
import com.gymduo.repo.CommentRepo;
import com.gymduo.repo.TopicRepo;
import com.gymduo.repo.UserRepo;

@RestController
@RequestMapping("/Forum")
public class ForumController {
	@Autowired TopicRepo topicRepo;
	@Autowired UserRepo userRepo;
	@Autowired CommentRepo commentRepo;
	
	@PostMapping("/CreateTopic")
	public Message createTopic(@RequestBody CreateTopicRequestModel model) {
		Topic topicFound = topicRepo.findByTitle(model.title);
		if (topicFound != null)
		{
			Message response = new Message("The topic is already created", 200);
			return response;
		}
		
		Topic newlyTopic = new Topic(model.title);
		Topic insertedTopic = topicRepo.insert(newlyTopic);
		if (insertedTopic == null)
		{
			Message msg = new Message("The topic could not be created. Please try again", 503);
			return msg;
		}
		else
		{
			Message msg = new Message("The topic is succesfully created!", 200);
			return msg;
		}
	}

	@PostMapping("/DeleteTopic")
	public Message deleteTopic(@RequestBody DeleteTopicRequestModel model)
	{
		Topic topicFound = topicRepo.findByTitle(model.title);
		if (topicFound == null)
		{
			Message response = new Message("Topic could not be found!", 404);
			return response;
		}
		
		topicRepo.delete(topicFound);
		
		Message msg = new Message("Topic succesfully deleted!", 200);
		return msg;
	}

	@PostMapping("AddComment")
	public Message addComment(@RequestBody CreateCommentRequestModel model)
	{
		User userFound = userRepo.findByAppKey(model.appKey);
		if (userFound == null)
		{
			Message msg = new Message("User could not be found.", 404);
			return msg;
		}
		
		Topic topicFound = topicRepo.findByTitle(model.topicTitle);
		if (topicFound == null)
		{
			Message msg = new Message("Topic could not be found.", 404);
			return msg;
		}
		
		Comment newComment = new Comment(userFound, topicFound, model.text);
		Comment insertedComment = commentRepo.insert(newComment);
		if (insertedComment == null)
		{
			Message msg = new Message("The comment could not be created. Please try again", 503);
			return msg;
		}
		else
		{
			Message msg = new Message("The comment is succesfully created!", 200);
			return msg;
		}
	}

	@PostMapping("DeleteComment")
	public Message deleteComment(@RequestBody DeleteCommentRequestModel model)
	{
		User userFound = userRepo.findByAppKey(model.appKey);
		if (userFound == null)
		{
			Message msg = new Message("User could not be found.", 404);
			return msg;
		}
		
		Optional<Comment> commentFound = commentRepo.findById(model.commentId);
		
		if (commentFound.isEmpty())
		{
			Message msg = new Message("Comment could not be found.", 404);
			return msg;
		}
		
		Comment comment = commentFound.get();
		
		commentRepo.delete(comment);
		
		Message msg = new Message("Comment succesfully deleted!", 200);
		return msg;
		
		
	}

	@GetMapping("/GetForumTopics")
	public Data<List<TopicViewModel>> getForumTopics()
	{
		List<Topic> topics = topicRepo.findAll();
		
		List<TopicViewModel> topicReponseData = topics.stream().map(topic -> 
								new TopicViewModel(topic.getTitle(), null)).collect(Collectors.toList());
		
		Data<List<TopicViewModel>> response = 
				new Data<List<TopicViewModel>>("Topic information succesfully obtained", 200, topicReponseData);
		return response;
	}

	@GetMapping("/GetForumTopicDetail/{topicTitle}")
	public Data<TopicViewModel> getForumTopicDetail(@PathVariable("topicTitle") String topicTitle)
	{	
		Topic topicFound = topicRepo.findByTitle(topicTitle);
		if (topicFound == null)
		{
			Data<TopicViewModel> response = new Data<TopicViewModel>("Topic could not be found.", 404, null);
			return response;
		}
		
		List<Comment> comments = commentRepo.findByTopic(topicFound);
		
		List<CommentViewModel> commentsParsed = comments.stream().map(comment -> {
			User user = comment.getUser();
			String fullName = user.getFirstName() + ' ' + user.getLastName();
			String pattern = "d MMM, yy";
			DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern(pattern);
			CommentViewModel commentModel = new CommentViewModel(fullName, simpleDateFormat.format(comment.getDateCreated()), comment.getText());
			return commentModel;
		}).collect(Collectors.toList());
		
		TopicViewModel topicData = new TopicViewModel(topicTitle, commentsParsed);
		
		Data<TopicViewModel> response = new Data<TopicViewModel>("Topic information succesfully obtained", 200, topicData);
		return response;
	}
}
