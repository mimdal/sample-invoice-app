package com.github.mimdal.api.web.invoice.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Setter
@Getter
@ToString
@Accessors(fluent = true)
public class InvoiceCalculateResponse {

	private Long invoiceId;

}
