package com.github.mimdal.service.user;

import com.github.mimdal.api.web.user.request.UserRegisterRequest;
import com.github.mimdal.api.web.user.response.UserRegisterResponse;
import com.github.mimdal.exception.UserRegistrationException;

public interface UserService {

	UserRegisterResponse register(Long customerId, UserRegisterRequest request) throws UserRegistrationException;

}
