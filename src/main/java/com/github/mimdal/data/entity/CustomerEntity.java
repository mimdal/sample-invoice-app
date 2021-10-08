package com.github.mimdal.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.github.mimdal.data.common.JpaBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Table(name = "CUSTOMER")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class CustomerEntity extends JpaBaseEntity {

	private static final long serialVersionUID = 2286776437232404120L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 3, max = 15, message = "name of customer should be minimum 3 and maximum 15 characters")
	private String name;

}
