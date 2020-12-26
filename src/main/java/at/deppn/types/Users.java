package at.deppn.types;

import com.google.gson.annotations.SerializedName;

public class Users {
	@SerializedName("name")
	private String name;
	@SerializedName("points")
	private double points;
	
	public String getName() {
		return name;
	}
	public double getPoints() {
		return points;
	}
}