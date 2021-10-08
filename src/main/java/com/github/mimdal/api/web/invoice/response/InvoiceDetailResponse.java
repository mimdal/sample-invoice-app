package com.github.mimdal.api.web.invoice.response;

import java.math.BigDecimal;

import com.github.mimdal.api.web.invoice.response.dto.InvoiceDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class InvoiceDetailResponse {

	private Long invoiceId;

	private BigDecimal totalPrice;

	private InvoiceDetails invoiceDetails;
}
