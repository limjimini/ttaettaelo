package com.tanine.ttaettaelo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

@Getter
public enum Gender {

	남자("남자"), 여자("여자"), 비밀("비밀");
	
    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() { // value 값이 사용할 수 있도록 지정
        return value;
    }

    @JsonCreator
    public static Gender fromValue(String value) { // enum으로 변환하는 메서드
        for (Gender gender : Gender.values()) {
            if (gender.getValue().equals(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown gender: " + value); //일치하지 않으면 예외
    }
}
