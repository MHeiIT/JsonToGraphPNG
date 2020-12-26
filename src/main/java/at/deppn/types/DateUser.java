package at.deppn.types;

import java.time.LocalDate;

import com.google.gson.annotations.SerializedName;

public class DateUser {
	@SerializedName("date")
	private LocalDate date;
	@SerializedName("users")
	private Users[] users;
	
	
	public LocalDate getDate() {
		return date;
	}
	public Users[] getUsers() {
		return users;
	}
}
