package com.github.mimdal.service.price;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.github.mimdal.api.web.invoice.response.dto.EventDto;
import com.github.mimdal.data.type.InvoiceState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceService_getEffectiveEvent_UTest {

	private SimpleDateFormat simpleDateFormat;

	@Mock
	private EventDto registration;

	@Mock
	private EventDto firstActivation;

	@Mock
	private EventDto secondActivation;

	@Mock
	private EventDto firstAppointment;

	private List<EventDto> events;



	@BeforeEach
	public void init() throws Exception {
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	@Test()
	public void whenUserHasTwoActivationInPeriod_thenEffectiveEventIsActive() {
		//User A.
		//Given
		Mockito.when(registration.type()).thenReturn(InvoiceState.REGISTER);
		Mockito.when(firstActivation.type()).thenReturn(InvoiceState.ACTIVE);
		Mockito.when(secondActivation.type()).thenReturn(InvoiceState.ACTIVE);
		events = Arrays.asList(registration, firstActivation, secondActivation);

		//When
		PriceService sut = new PriceServiceImpl();
		InvoiceState result = sut.getEffectiveEvent(events);

		//Then
		Assertions.assertEquals(InvoiceState.ACTIVE, result);
	}

	@Test
	public void whenUserHasOneActivationAndOneAppointmentInPeriod_thenEffectiveEventIsAppointment() {
		//User D.
		//Given
		Mockito.when(registration.type()).thenReturn(InvoiceState.REGISTER);
		Mockito.when(firstActivation.type()).thenReturn(InvoiceState.ACTIVE);
		Mockito.when(firstAppointment.type()).thenReturn(InvoiceState.APPOINTMENT);
		events = Arrays.asList(registration, firstActivation, firstAppointment);

		//When
		PriceService sut = new PriceServiceImpl();
		InvoiceState result = sut.getEffectiveEvent(events);

		//Then
		Assertions.assertEquals(InvoiceState.APPOINTMENT, result);
	}


	private Date stringToDate(String stringDate) throws ParseException {
		return simpleDateFormat.parse(stringDate);
	}

}