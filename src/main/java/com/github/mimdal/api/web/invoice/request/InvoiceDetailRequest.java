package com.github.mimdal.api.web.invoice.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class InvoiceDetailRequest {

	@NotNull
	private Long invoiceId;

}
