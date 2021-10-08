package com.github.mimdal.data.dao;

import java.util.Date;
import java.util.List;

import com.github.mimdal.data.entity.EventEntity;
import com.github.mimdal.data.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDao extends JpaRepository<EventEntity, Long> {

	List<EventEntity> findAllByUserInAndDateBetween(List<UserEntity> userList, Date start, Date end);
}
