package com.github.mimdal.api.web.user.request;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserRegisterRequest {

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date registerDate;

	@Size(min = 3, max = 15, message = "name of customer should be minimum 3 and maximum 15 characters")
	private String name;

	@Email(message = "Email should be valid")
	private String email;

}
