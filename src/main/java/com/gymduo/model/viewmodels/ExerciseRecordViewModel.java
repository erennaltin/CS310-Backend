package com.gymduo.model.viewmodels;

public class ExerciseRecordViewModel {
	public String exerciseId;
	public int weight;
	public int maxRep;
	public double currentWeight;
	public String date;
	
	public ExerciseRecordViewModel() {
		// TODO Auto-generated constructor stub
	}

	public ExerciseRecordViewModel(String exerciseId, int weight, int maxRep, double currentWeight, String date) {
		super();
		this.exerciseId = exerciseId;
		this.weight = weight;
		this.maxRep = maxRep;
		this.currentWeight = currentWeight;
		this.date = date;
	}
	
	
}
