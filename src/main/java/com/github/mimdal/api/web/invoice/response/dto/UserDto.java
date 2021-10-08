package com.github.mimdal.api.web.invoice.response.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.mimdal.data.type.InvoiceState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UserDto {

	private Long id;

	private String name;

	private String email;

	@JsonIgnore
	private InvoiceState invoiceState;

	/**
	 * special toString implement for key json serial and deserialization, DON'T Change it!!!
	 */
	@Override
	public String toString() {
		return "{\"id\":" + id
				+ ", \"name\":\"" + name
				+ "\", \"email\":\"" + email
				+ "\"}";
	}

}
