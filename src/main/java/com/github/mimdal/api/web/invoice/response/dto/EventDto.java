package com.github.mimdal.api.web.invoice.response.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.mimdal.data.type.InvoiceState;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class EventDto {

	@JsonIgnore
	private UserDto userDto;

	private InvoiceState type;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date eventDate;

}
