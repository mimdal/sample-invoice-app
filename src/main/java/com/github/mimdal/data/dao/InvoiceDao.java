package com.github.mimdal.data.dao;

import com.github.mimdal.data.entity.InvoiceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDao extends JpaRepository<InvoiceEntity, Long> {
}
