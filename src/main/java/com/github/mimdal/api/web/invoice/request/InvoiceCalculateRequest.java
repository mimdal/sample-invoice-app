package com.github.mimdal.api.web.invoice.request;

import java.util.Date;

import javax.validation.constraints.AssertTrue;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class InvoiceCalculateRequest {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;

	@AssertTrue(message = "end date should be after start date!")
	public boolean isEndDate() {
		return endDate.compareTo(startDate) >= 0;
	}

}
