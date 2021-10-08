package com.github.mimdal.api.web.user;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.github.mimdal.api.web.user.request.UserRegisterRequest;
import com.github.mimdal.api.web.user.response.UserRegisterResponse;
import com.github.mimdal.common.constant.Constants;
import com.github.mimdal.exception.UserRegistrationException;
import com.github.mimdal.service.user.UserService;
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
@RequestMapping(path = "/users")
@Transactional
public class UserResource {

	private final UserService service;

	@PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest request,
			@RequestHeader(name = Constants.CUSTOMER_ID) Long customerId) throws UserRegistrationException {
		UserRegisterResponse response = service.register(customerId, request);
		return ResponseEntity.ok(response);
	}
}
