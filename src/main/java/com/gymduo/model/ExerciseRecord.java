package com.gymduo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("exerciseRecords")
public class ExerciseRecord extends BaseModel{
	@Id
	private String id;
	
	@DBRef
	private User user;
	
	@DBRef
	private Exercise exercise;
	
	private int weight;
	private int maxRep;
	private double currentWeight;
	
	public ExerciseRecord() {
		// TODO Auto-generated constructor stub
	}

	public ExerciseRecord(User user, Exercise exercise, int weight, int maxRep, double currentWeight) {
		super();
		this.user = user;
		this.exercise = exercise;
		this.weight = weight;
		this.maxRep = maxRep;
		this.currentWeight = currentWeight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getMaxRep() {
		return maxRep;
	}

	public void setMaxRep(int maxRep) {
		this.maxRep = maxRep;
	}

	public double getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(int currentWeight) {
		this.currentWeight = currentWeight;
	}

	public String getId() {
		return id;
	}
	
	
}
