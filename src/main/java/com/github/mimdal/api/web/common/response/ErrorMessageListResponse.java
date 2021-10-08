package com.github.mimdal.api.web.common.response;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.data.util.Pair;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorMessageListResponse extends GeneralResponse {

	private String message;

	private List<Pair<?, ?>> errorMessages;

}
