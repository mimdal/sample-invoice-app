package com.github.mimdal.service.user;

import java.util.Optional;

import com.github.mimdal.api.web.user.request.UserRegisterRequest;
import com.github.mimdal.api.web.user.response.UserRegisterResponse;
import com.github.mimdal.data.dao.CustomerDao;
import com.github.mimdal.data.dao.UserDao;
import com.github.mimdal.data.entity.CustomerEntity;
import com.github.mimdal.data.entity.UserEntity;
import com.github.mimdal.data.type.InvoiceState;
import com.github.mimdal.exception.UserRegistrationException;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	private final CustomerDao customerDao;

	@Override
	public UserRegisterResponse register(Long customerId, UserRegisterRequest request) throws UserRegistrationException {
		Optional<UserEntity> persistedUser = userDao.findUserEntityByEmailEquals(request.getEmail());
		if (persistedUser.isPresent()) {
			throw new UserRegistrationException("email have registered before!");
		}
		CustomerEntity persistedCustomer = customerDao.findCustomerOrThrow(customerId);

		UserEntity user = UserEntity.builder()
				.registerDate(request.getRegisterDate())
				.email(request.getEmail())
				.invoiceState(InvoiceState.NOT_INVOICED)
				.name(request.getName())
				.customer(persistedCustomer)
				.build();
		UserEntity userEntity = userDao.save(user);
		return UserRegisterResponse.builder()
				.id(userEntity.getId())
				.email(userEntity.getEmail())
				.build();
	}
}
