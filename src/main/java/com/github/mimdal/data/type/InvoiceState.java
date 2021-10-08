package com.github.mimdal.data.type;

import lombok.Getter;

@Getter
public enum InvoiceState {

	NOT_INVOICED(0),
	REGISTER(1),
	ACTIVE(2),
	APPOINTMENT(3);

	private final Integer priority;

	InvoiceState(Integer priority) {
		this.priority = priority;
	}
}
