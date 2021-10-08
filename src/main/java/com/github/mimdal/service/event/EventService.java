package com.github.mimdal.service.event;

import com.github.mimdal.api.web.event.request.ActiveEventRequest;
import com.github.mimdal.api.web.event.request.AppointmentEventRequest;
import com.github.mimdal.exception.EntitiesNotRelatedException;
import com.github.mimdal.exception.EntityNotFoundException;

public interface EventService {

	void activeEvent(Long customerId, Long userId, ActiveEventRequest request) throws EntityNotFoundException, EntitiesNotRelatedException;

	void appointmentEvent(Long customerId, Long userId, AppointmentEventRequest request) throws EntityNotFoundException, EntitiesNotRelatedException;

}
