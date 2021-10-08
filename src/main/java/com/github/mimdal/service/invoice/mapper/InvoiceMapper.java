package com.github.mimdal.service.invoice.mapper;

import java.util.List;

import com.github.mimdal.api.web.invoice.response.dto.EventDto;
import com.github.mimdal.api.web.invoice.response.dto.UserDto;
import com.github.mimdal.data.entity.EventEntity;
import com.github.mimdal.data.entity.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface InvoiceMapper {

	@BeanMapping(ignoreByDefault = true)
	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "email", source = "email")
	@Mapping(target = "invoiceState", source = "invoiceState")
	UserDto toUserDto(UserEntity user);


	List<EventDto> toEventDto(List<EventEntity> events);

	default EventDto toEventDto(EventEntity event) {
		return new EventDto()
				.userDto(toUserDto(event.getUser()))
				.type(event.getType().getEquivalentInvoiceState())
				.eventDate(event.getDate());

	}

}
