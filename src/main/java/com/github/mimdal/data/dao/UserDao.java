package com.github.mimdal.data.dao;

import java.util.List;
import java.util.Optional;

import com.github.mimdal.data.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findUserEntityByEmailEquals(String searchEmail);

	List<UserEntity> findAllByCustomer_Id(Long customerId);

}
