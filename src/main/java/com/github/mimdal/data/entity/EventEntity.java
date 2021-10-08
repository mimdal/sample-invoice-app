package com.github.mimdal.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.github.mimdal.data.common.JpaBaseEntity;
import com.github.mimdal.data.type.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EVENT")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EventEntity extends JpaBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EventType type;

	@ManyToOne(optional = false)
	private UserEntity user;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date date;

}
