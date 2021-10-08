package com.github.mimdal.api.web.event.request;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class RegisterEventRequest {

	private Date registerDate;

}
