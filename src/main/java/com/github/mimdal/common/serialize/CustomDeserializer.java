package com.github.mimdal.common.serialize;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.github.mimdal.api.web.invoice.response.dto.UserDto;
import com.github.mimdal.common.util.JsonMapper;

public class CustomDeserializer extends KeyDeserializer {

	@Override
	public UserDto deserializeKey(String key, DeserializationContext ctxt) throws IOException {
		key = key.replace("UserDto", "")
				.replace("(", "{\"")
				.replace(")", "\"}")
				.replace(", ", "\", \"")
				.replace("=", "\":\"");
		return JsonMapper.jsonStringToObject(key, UserDto.class);
	}
}