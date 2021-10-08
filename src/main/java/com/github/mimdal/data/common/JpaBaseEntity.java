package com.github.mimdal.data.common;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JpaBaseEntity implements Serializable {

	private static final long serialVersionUID = -5922401507906062818L;

	@Id
	@GeneratedValue(
			strategy = GenerationType.IDENTITY
	)
	private Long id;
}
