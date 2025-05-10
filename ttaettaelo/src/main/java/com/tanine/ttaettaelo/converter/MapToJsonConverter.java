package com.tanine.ttaettaelo.converter;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MapToJsonConverter implements AttributeConverter<Map<String, String>, String> {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Map<String, String> attribute) {
		try {
			return objectMapper.writeValueAsString(attribute); // Map -> JSON으로 변환
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Error converting Map to JSON", e);
		}
	}

	@Override
	public Map<String, String> convertToEntityAttribute(String dbData) {
		try {
			return objectMapper.readValue(dbData, Map.class); // JSON -> Map으로 변환
		} catch (IOException e) {
			throw new IllegalArgumentException("Error converting JSON to Map", e);
		}
	}

}
