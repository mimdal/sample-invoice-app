package com.github.mimdal.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.mimdal.exception.ServiceRuntimeException;

public final class JsonMapper {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private JsonMapper() {
	}

	public static ObjectNode jsonStringToObjectNode(String config) throws JsonProcessingException {
		return OBJECT_MAPPER.readValue(config, ObjectNode.class);
	}

	public static <T> T jsonStringToObject(String jsonString, Class<T> objectType) {
		try {
			return OBJECT_MAPPER.readValue(jsonString, objectType);
		} catch (JsonProcessingException e) {
			throw new ServiceRuntimeException("Exception happen in json String To Object", e);
		}
	}

	public static String objectNodeToJsonString(Object objectNode) {
		try {
			return OBJECT_MAPPER.writeValueAsString(objectNode);
		} catch (JsonProcessingException e) {
			throw new ServiceRuntimeException("Exception happen in objectNode convert to json String", e);
		}
	}

	public static ObjectNode objectToObjectNode(Object object) {
		return OBJECT_MAPPER.valueToTree(object);
	}
}

