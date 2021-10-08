package com.github.mimdal.api.web.event.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.github.mimdal.api.web.common.response.GeneralResponse;
import com.github.mimdal.api.web.event.request.ActiveEventRequest;
import com.github.mimdal.api.web.event.request.AppointmentEventRequest;
import com.github.mimdal.common.constant.Constants;
import com.github.mimdal.exception.EntitiesNotRelatedException;
import com.github.mimdal.exception.EntityNotFoundException;
import com.github.mimdal.service.event.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/events")
@Transactional
public class EventResource {

	private final EventService service;

	@PostMapping(path = "/active", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> activeEvent(@Valid @RequestBody ActiveEventRequest request,
			@RequestHeader(name = Constants.CUSTOMER_ID) Long customerId,
			@RequestHeader(name = Constants.USER_ID) Long userId) throws EntityNotFoundException, EntitiesNotRelatedException {
		service.activeEvent(customerId, userId, request);
		return ResponseEntity.ok(new GeneralResponse(Constants.SUCCESS_RESPONSE));
	}

	@PostMapping(path = "/appointment", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> appointmentEvent(@Valid @RequestBody AppointmentEventRequest request,
			@RequestHeader(name = Constants.CUSTOMER_ID) Long customerId,
			@RequestHeader(name = Constants.USER_ID) Long userId) throws EntityNotFoundException, EntitiesNotRelatedException {
		service.appointmentEvent(customerId, userId, request);
		return ResponseEntity.ok(new GeneralResponse(Constants.SUCCESS_RESPONSE));
	}
}
