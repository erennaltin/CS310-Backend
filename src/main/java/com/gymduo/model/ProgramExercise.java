package com.gymduo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("programExercises")
public class ProgramExercise extends BaseModel {
	@Id
	private String id;
	
	@DBRef
	private Program program;
	
	@DBRef
	private Exercise exercise;
	
	private int reps;
	private int sets;
	
	public ProgramExercise() {
		// TODO Auto-generated constructor stub
	}

	public ProgramExercise(Program program, Exercise exercise, int reps, int sets) {
		super();
		this.program = program;
		this.exercise = exercise;
		this.reps = reps;
		this.sets = sets;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}
	
	
}
