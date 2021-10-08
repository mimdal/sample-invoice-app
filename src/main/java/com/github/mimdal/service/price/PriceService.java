package com.github.mimdal.service.price;

import java.math.BigDecimal;
import java.util.List;

import com.github.mimdal.api.web.invoice.response.dto.EventDto;
import com.github.mimdal.api.web.invoice.response.dto.UserDto;
import com.github.mimdal.data.type.InvoiceState;

public interface PriceService {

	BigDecimal getEventPrice(InvoiceState eventType);

	InvoiceState getEffectiveEvent(List<EventDto> events);

	BigDecimal getPriceBasedOnEffectiveStatus(UserDto user, InvoiceState state);

}
