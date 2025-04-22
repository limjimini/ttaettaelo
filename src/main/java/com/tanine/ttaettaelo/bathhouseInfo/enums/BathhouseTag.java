package com.tanine.ttaettaelo.bathhouseInfo.enums;

import lombok.Getter;

@Getter
public enum BathhouseTag {

	SAUNA("타입", "사우나"), BATHHOUSE("타입", "목욕탕"), MOBILEBATH("타입", "이동식 목욕탕"), OPENAIRBATH("타입", "노천탕"),
	SCRUB("타입", "세신샵"), MASSAGE("타입", "마사지"), JJIMJILBANG("타입", "찜질방"),
	
	SOLO("대상", "1인"), FAMILY("대상", "가족"), COUPLE("대상", "커플"), WOMEN("대상", "여성 전용"), MEN("대상", "남성 전용"), DISABLED("대상", "장애인"),
	
	RESERVATION("예약", "예약 필수"),
	
	FULL_TIME("운영", "24시간 영업"), NIGHT("운영", "야간 운영"),
	
	PARKING("교통", "주차 가능"),
	
	SEOUL("지역", "서울"), INCHEON("지역", "인천"), DAEJEON("지역", "대전"), DAEGU("지역", "대구"),
	GWANGJU("지역", "광주"), BUSAN("지역", "부산"), ULSAN("지역", "울산"), GYEONGGI("지역", "경기"),
	GANGWON("지역", "강원"), CHUNGBUK("지역", "충북"), CHUNGNAM("지역", "충남"), GYEONGBUK("지역", "경북"),
	GYEONGNAM("지역", "경남"), JEONBUK("지역", "전북"), JEONNAM("지역", "전남"), JEJU("지역", "제주"), SEJONG("지역", "세종");
	
	private final String category;
	private final String tag;
	
	BathhouseTag(String category, String tag) {
		this.category = category;
		this.tag = tag;
	}
}
