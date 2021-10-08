package com.github.mimdal.service.mapper;

import java.util.Date;

import com.github.mimdal.data.entity.EventEntity;
import com.github.mimdal.data.entity.UserEntity;
import com.github.mimdal.data.type.EventType;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EventMapper {

	@BeanMapping(ignoreByDefault = true)
	@Mapping(target = "user", source = "user")
	@Mapping(target = "date", source = "date")
	@Mapping(target = "type", source = "type")
	EventEntity toEvent(UserEntity user, Date date, EventType type);

}
