package com.github.mimdal.data.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.github.mimdal.api.web.invoice.response.dto.InvoiceDetails;
import com.github.mimdal.common.util.JsonMapper;
import com.github.mimdal.data.common.JpaBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "INVOICE")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class InvoiceEntity extends JpaBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = CustomerEntity.class)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private CustomerEntity customer;

	@Column(nullable = false)
	private BigDecimal totalPrice;

	@Lob
	@Column(nullable = false)
	private String invoiceDetails;
	
	public InvoiceDetails getInvoiceDetails() {
		return JsonMapper.jsonStringToObject(invoiceDetails, InvoiceDetails.class);
	}

	public void setInvoiceItemsHistory(InvoiceDetails invoiceDetailsHistory) {
		this.invoiceDetails = JsonMapper.objectNodeToJsonString(invoiceDetailsHistory);
	}

}
