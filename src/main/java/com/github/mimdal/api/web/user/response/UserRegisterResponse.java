package com.github.mimdal.api.web.user.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterResponse {

	private Long id;

	private String email;

}
