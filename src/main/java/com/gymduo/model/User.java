package com.gymduo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gymduo.enums.BMI;
import com.gymduo.enums.ExperienceLevel;

@Document("users")
public class User extends BaseModel {
	@Id
	private String id;
	
	private String firstName;
	private String lastName;
	private double weight;
	private int height;
	private ExperienceLevel experienceLevel;
	private String appKey;
	

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String appKey) {
		super();
		this.appKey = appKey;
	}
	
	public User(String firstName, String lastName, double weight, int height, String appKey, ExperienceLevel experienceLevel) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.weight = weight;
		this.height = height;
		this.appKey = appKey;
		this.experienceLevel = experienceLevel;
	}

	public ExperienceLevel getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(ExperienceLevel experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getId() {
		return id;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	public BMI calculateBMI()
	{
		double calculatedBMI = weight / Math.pow((double) height / 100, 2);

		if (calculatedBMI <= 18.5)
		{
			return BMI.UNDERWEIGHT;
		}
		else if (18.5 < calculatedBMI && calculatedBMI <= 25)
		{
			return BMI.NORMAL;
		}
		else if (25 < calculatedBMI && calculatedBMI <= 30)
		{
			return BMI.OVERWEIGHT;
		}
		else
		{
			return BMI.OBESITY;
		}
	}
	
}
