package com.github.mimdal.data.type;

import lombok.Getter;

@Getter
public enum EventType {
	ACTIVE(InvoiceState.ACTIVE),
	APPOINTMENT(InvoiceState.APPOINTMENT);

	private InvoiceState equivalentInvoiceState;

	EventType(InvoiceState equivalentInvoiceState) {
		this.equivalentInvoiceState = equivalentInvoiceState;
	}
}
