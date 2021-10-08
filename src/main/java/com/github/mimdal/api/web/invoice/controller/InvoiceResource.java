package com.github.mimdal.api.web.invoice.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.github.mimdal.api.web.invoice.request.InvoiceCalculateRequest;
import com.github.mimdal.api.web.invoice.response.InvoiceCalculateResponse;
import com.github.mimdal.api.web.invoice.response.InvoiceDetailResponse;
import com.github.mimdal.common.constant.Constants;
import com.github.mimdal.exception.EntitiesNotRelatedException;
import com.github.mimdal.exception.EntityNotFoundException;
import com.github.mimdal.service.invoice.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/invoices")
@Transactional
public class InvoiceResource {

	private final InvoiceService service;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceCalculateResponse> invoiceGenerate(@Valid @RequestBody InvoiceCalculateRequest request,
			@RequestHeader(name = Constants.CUSTOMER_ID) Long customerId) {
		InvoiceCalculateResponse response = service.invoiceGenerate(request, customerId);
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{invoiceId}", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<InvoiceDetailResponse> getInvoiceDetail(@PathVariable Long invoiceId,
			@RequestHeader(name = Constants.CUSTOMER_ID) Long customerId) throws EntitiesNotRelatedException, EntityNotFoundException {
		InvoiceDetailResponse response = service.getInvoiceDetail(invoiceId, customerId);
		return ResponseEntity.ok(response);
	}

}
