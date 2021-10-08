package com.github.mimdal.service.price;

import java.math.BigDecimal;
import java.util.List;

import com.github.mimdal.api.web.invoice.response.dto.EventDto;
import com.github.mimdal.api.web.invoice.response.dto.UserDto;
import com.github.mimdal.data.type.InvoiceState;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

	@Override
	public BigDecimal getEventPrice(InvoiceState type) {
		BigDecimal eventFee;
		switch (type) {
			case NOT_INVOICED:
				eventFee = BigDecimal.ZERO;
				break;
			case REGISTER:
				eventFee = BigDecimal.valueOf(0.49);
				break;
			case ACTIVE:
				eventFee = BigDecimal.valueOf(0.99);
				break;
			case APPOINTMENT:
				eventFee = BigDecimal.valueOf(3.99);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + type);
		}
		return eventFee;
	}

	@Override
	public InvoiceState getEffectiveEvent(List<EventDto> events) {
		return events.stream()
				.map(EventDto::type)
				.reduce((typeOne, typeTwo) -> {
					if (typeOne.getPriority() >= typeTwo.getPriority()) {
						return typeOne;
					} else {
						return typeTwo;
					}
				}).orElse(InvoiceState.NOT_INVOICED);
	}

	@Override
	public BigDecimal getPriceBasedOnEffectiveStatus(UserDto user, InvoiceState state) {
		return getEventPrice(state).subtract(getEventPrice(user.getInvoiceState()));
	}
}
