package com.github.mimdal.service.invoice;

import com.github.mimdal.api.web.invoice.request.InvoiceCalculateRequest;
import com.github.mimdal.api.web.invoice.response.InvoiceCalculateResponse;
import com.github.mimdal.api.web.invoice.response.InvoiceDetailResponse;
import com.github.mimdal.exception.EntitiesNotRelatedException;
import com.github.mimdal.exception.EntityNotFoundException;

public interface InvoiceService {

	InvoiceCalculateResponse invoiceGenerate(InvoiceCalculateRequest request, Long customerId);

	InvoiceDetailResponse getInvoiceDetail(Long invoiceId, Long customerId) throws EntityNotFoundException, EntitiesNotRelatedException;
}
