package com.tanine.ttaettaelo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

@Getter
public enum BathhouseType {
	
	목욕탕("목욕탕"), 사우나("사우나"), 찜질방("찜질방");
	
	private final String value;
	
	BathhouseType(String value) {
		this.value = value;
	}
	
    @JsonValue
    public String getValue() { // value 값이 사용할 수 있도록 지정
        return value;
    }

    @JsonCreator
    public static BathhouseType fromValue(String value) { // enum으로 변환하는 메서드
        for (BathhouseType type : BathhouseType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown type: " + value); //일치하지 않으면 예외
    }
}
