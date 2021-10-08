package com.github.mimdal.service.price;

import java.math.BigDecimal;

import com.github.mimdal.api.web.invoice.response.dto.UserDto;
import com.github.mimdal.data.type.InvoiceState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceService_getPriceBasedOnEffectiveStatus_UTest {

	@Mock
	private UserDto user;

	private InvoiceState effectiveEvent;

	@Test
	public void whenUserRegisteredBeforeAndHasAtLeastOneActiveSession() {
		//User A.
		//Given
		//user registered before and customer paid registration bill
		Mockito.when(user.getInvoiceState()).thenReturn(InvoiceState.REGISTER);
		effectiveEvent = InvoiceState.ACTIVE;

		//When
		PriceService sut = new PriceServiceImpl();
		BigDecimal result = sut.getPriceBasedOnEffectiveStatus(user, effectiveEvent);

		//Then
		Assertions.assertEquals(new BigDecimal("0.50"), result,
				"registered before (and payed) but at least one activation in period");
	}

	@Test
	public void whenUserRegisteredBeforeAndHasAtLeastOneAppointmentSession() {
		//User B.
		//Given
		//user registered before and customer paid registration bill
		Mockito.when(user.getInvoiceState()).thenReturn(InvoiceState.REGISTER);
		effectiveEvent = InvoiceState.APPOINTMENT;

		//When
		PriceService sut = new PriceServiceImpl();
		BigDecimal result = sut.getPriceBasedOnEffectiveStatus(user, effectiveEvent);

		//Then
		Assertions.assertEquals(new BigDecimal("3.50"), result,
				"registered before, but made appointment in period");
	}


	@Test
	public void whenUserHasAtLeastOneActiveSessionAndCustomerPaidNothing() {
		//User C.
		//Given
		//user registered before and customer paid registration bill
		Mockito.when(user.getInvoiceState()).thenReturn(InvoiceState.NOT_INVOICED);
		effectiveEvent = InvoiceState.ACTIVE;

		//When
		PriceService sut = new PriceServiceImpl();
		BigDecimal result = sut.getPriceBasedOnEffectiveStatus(user, effectiveEvent);

		//Then
		Assertions.assertEquals(new BigDecimal("0.99"), result,
				"2 events in period, highest price is activation");
	}

	@Test
	public void whenUserHasAtLeastOneActiveAndOneAppointmentSessionAndCustomerPaidAllSessionsBefore() {
		//User D.
		//Given
		//user registered before and customer paid registration bill
		Mockito.when(user.getInvoiceState()).thenReturn(InvoiceState.APPOINTMENT);
		effectiveEvent = InvoiceState.APPOINTMENT;

		//When
		PriceService sut = new PriceServiceImpl();
		BigDecimal result = sut.getPriceBasedOnEffectiveStatus(user, effectiveEvent);

		//Then
		Assertions.assertEquals(new BigDecimal("0.00"), result,
				"events happened at least once before billing");
	}
}
