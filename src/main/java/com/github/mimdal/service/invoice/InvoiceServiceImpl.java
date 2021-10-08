package com.github.mimdal.service.invoice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.mimdal.api.web.invoice.request.InvoiceCalculateRequest;
import com.github.mimdal.api.web.invoice.response.InvoiceCalculateResponse;
import com.github.mimdal.api.web.invoice.response.InvoiceDetailResponse;
import com.github.mimdal.api.web.invoice.response.dto.EventDto;
import com.github.mimdal.api.web.invoice.response.dto.InvoiceDetails;
import com.github.mimdal.api.web.invoice.response.dto.UserDto;
import com.github.mimdal.data.dao.CustomerDao;
import com.github.mimdal.data.dao.EventDao;
import com.github.mimdal.data.dao.InvoiceDao;
import com.github.mimdal.data.dao.UserDao;
import com.github.mimdal.data.entity.CustomerEntity;
import com.github.mimdal.data.entity.InvoiceEntity;
import com.github.mimdal.data.entity.UserEntity;
import com.github.mimdal.data.type.InvoiceState;
import com.github.mimdal.exception.EntitiesNotRelatedException;
import com.github.mimdal.exception.EntityNotFoundException;
import com.github.mimdal.service.invoice.mapper.InvoiceMapper;
import com.github.mimdal.service.price.PriceService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

	private final CustomerDao customerDao;

	private final UserDao userDao;

	private final EventDao eventDao;

	private final InvoiceDao invoiceDao;

	private final PriceService priceService;

	private final InvoiceMapper mapper;

	@Override
	public InvoiceCalculateResponse invoiceGenerate(InvoiceCalculateRequest request, Long customerId) {
		List<UserEntity> usersEntity = userDao.findAllByCustomer_Id(customerId);
		List<EventDto> events = convertEntityToEventDto(usersEntity, request.getStartDate(), request.getEndDate());
		List<UserDto> users = convertEntityToUserDto(usersEntity);
		InvoiceEntity invoice = invoiceCalculate(users, events, customerId);
		invoiceDao.save(invoice);
		return new InvoiceCalculateResponse().invoiceId(invoice.getId());
	}

	private InvoiceEntity invoiceCalculate(List<UserDto> users, List<EventDto> events, Long customerId) {
		CustomerEntity customer = customerDao.findCustomerOrThrow(customerId);
		BigDecimal totalPrice = BigDecimal.ZERO;
		Map<UserDto, List<EventDto>> userItems = new HashMap<>(users.size());
		Map<UserDto, BigDecimal> userNetPrice = new HashMap<>(users.size());
		EnumMap<InvoiceState, BigDecimal> priceHistory = priceHistoryCalculation();
		InvoiceDetails invoiceDetails = InvoiceDetails.builder()
				.userItems(userItems)
				.userNetPrice(userNetPrice)
				.priceHistory(priceHistory)
				.build();
		for (UserDto user : users) {
			List<EventDto> userEvents = events.stream()
					.filter(eventDto -> eventDto.userDto().equals(user))
					.collect(Collectors.toList());
			InvoiceState effectiveUserEvent = priceService.getEffectiveEvent(events);
			BigDecimal netPrice = priceService.getPriceBasedOnEffectiveStatus(user, effectiveUserEvent);

			totalPrice = totalPrice.add(netPrice);
			userNetPrice.put(user, netPrice);
			userItems.put(user, userEvents);
			user.setInvoiceState(effectiveUserEvent);
		}

		InvoiceEntity invoice = new InvoiceEntity();
		invoice.setTotalPrice(totalPrice);
		invoice.setCustomer(customer);
		invoice.setInvoiceItemsHistory(invoiceDetails);
		return invoice;
	}

	private EnumMap<InvoiceState, BigDecimal> priceHistoryCalculation() {
		EnumMap<InvoiceState, BigDecimal> priceHistory = new EnumMap<>(InvoiceState.class);
		priceHistory.put(InvoiceState.REGISTER, priceService.getEventPrice(InvoiceState.REGISTER));
		priceHistory.put(InvoiceState.ACTIVE, priceService.getEventPrice(InvoiceState.ACTIVE));
		priceHistory.put(InvoiceState.APPOINTMENT, priceService.getEventPrice(InvoiceState.APPOINTMENT));
		return priceHistory;
	}

	private List<UserDto> convertEntityToUserDto(List<UserEntity> users) {
		return users.stream()
				.map(mapper::toUserDto)
				.collect(Collectors.toList());
	}

	private List<EventDto> convertEntityToEventDto(List<UserEntity> users, Date start, Date end) {
		List<EventDto> usersRegisterEvent = getRegistrationEvents(users, start, end);
		List<EventDto> events = eventDao.findAllByUserInAndDateBetween(users, start, end).stream()
				.map(mapper::toEventDto)
				.collect(Collectors.toList());
		events.addAll(usersRegisterEvent);
		return events;
	}

	private List<EventDto> getRegistrationEvents(List<UserEntity> users, Date start, Date end) {
		return users.stream()
				.filter(userEntity -> isBetweenInvoiceDates(userEntity.getRegisterDate(), start, end))
				.map(userEntity -> new EventDto()
						.eventDate(userEntity.getRegisterDate())
						.type(InvoiceState.REGISTER)
						.userDto(mapper.toUserDto(userEntity)))
				.collect(Collectors.toList());
	}

	private boolean isBetweenInvoiceDates(Date compareDate, Date start, Date end) {
		return compareDate.after(start) && compareDate.before(end);
	}

	@Override
	public InvoiceDetailResponse getInvoiceDetail(Long invoiceId, Long customerId)
			throws EntityNotFoundException, EntitiesNotRelatedException {
		InvoiceEntity invoice = invoiceDao.findById(invoiceId)
				.orElseThrow(() -> new EntityNotFoundException("Invoice not found! invoiceId: " + invoiceId));
		checkRelation(invoice, customerId);
		return InvoiceDetailResponse.builder()
				.invoiceId(invoice.getId())
				.totalPrice(invoice.getTotalPrice())
				.invoiceDetails(invoice.getInvoiceDetails())
				.build();
	}

	private void checkRelation(InvoiceEntity invoice, Long customerId) throws EntitiesNotRelatedException {
		if (!invoice.getCustomer().getId().equals(customerId)) {
			throw new EntitiesNotRelatedException("invoice not related to customer! customerId: " + customerId +
					" invoiceId: " + invoice.getId());
		}
	}
}
