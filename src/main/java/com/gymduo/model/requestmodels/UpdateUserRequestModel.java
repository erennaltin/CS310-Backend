package com.gymduo.model.requestmodels;

import com.gymduo.enums.ExperienceLevel;

public class UpdateUserRequestModel extends FrontendRequestModel {
	public String firstName;
	public String lastName;
	public double weight;
	public int height;
	public ExperienceLevel experienceLevel;

}
