package com.github.mimdal.service.event;

import com.github.mimdal.api.web.event.request.ActiveEventRequest;
import com.github.mimdal.api.web.event.request.AppointmentEventRequest;
import com.github.mimdal.data.dao.EventDao;
import com.github.mimdal.data.dao.UserDao;
import com.github.mimdal.data.entity.UserEntity;
import com.github.mimdal.data.type.EventType;
import com.github.mimdal.exception.EntitiesNotRelatedException;
import com.github.mimdal.exception.EntityNotFoundException;
import com.github.mimdal.service.mapper.EventMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final UserDao userDao;

	private final EventDao eventDao;

	private final EventMapper mapper;

	@Override
	public void activeEvent(Long customerId, Long userId, ActiveEventRequest request)
			throws EntityNotFoundException, EntitiesNotRelatedException {
		UserEntity user = getUser(userId);
		checkRelation(customerId, user);
		eventDao.save(mapper.toEvent(user, request.getDate(), EventType.ACTIVE));
	}

	@Override
	public void appointmentEvent(Long customerId, Long userId, AppointmentEventRequest request)
			throws EntityNotFoundException, EntitiesNotRelatedException {
		UserEntity user = getUser(userId);
		checkRelation(customerId, user);
		eventDao.save(mapper.toEvent(user, request.getDate(), EventType.APPOINTMENT));
	}

	private UserEntity getUser(Long userId) throws EntityNotFoundException {
		return userDao.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found! userId: " + userId));
	}

	private void checkRelation(Long customerId, UserEntity user) throws EntitiesNotRelatedException {
		if (!user.getCustomer().getId().equals(customerId)) {
			throw new EntitiesNotRelatedException("user not related to customer! customerId: " + customerId + " userId: " + user.getId());
		}
	}
}
