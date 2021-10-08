package com.github.mimdal.api.web.invoice.response.dto;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.mimdal.common.serialize.CustomDeserializer;
import com.github.mimdal.data.type.InvoiceState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetails {

	@JsonDeserialize(keyUsing = CustomDeserializer.class)
	private Map<UserDto, List<EventDto>> userItems;

	@JsonDeserialize(keyUsing = CustomDeserializer.class)
	private Map<UserDto, BigDecimal> userNetPrice;

	private EnumMap<InvoiceState, BigDecimal> priceHistory;

}
