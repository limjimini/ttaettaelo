package com.tanine.ttaettaelo.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tanine.ttaettaelo.dto.SupportDTO;
import com.tanine.ttaettaelo.mapper.LoginMapper;
import com.tanine.ttaettaelo.mapper.SupportMapper;

import lombok.RequiredArgsConstructor;

/**
 * 문의하기 관련 서비스를 처리하는 클래스
 * 문의글과 답변 조회, 문의글 등록, 문의 상태 업데이트와 같은 기능을 제공한다.
 */
@Service
@RequiredArgsConstructor
public class SupportService {

	private final SupportMapper supportMapper;
	
	/**
	 * 문의글과 문의 답변 같이 조회
	 * @param pageNumber 조회할 페이지 번호
	 * @param pageSize 한 페이지에 나타낼 문의글 수
	 * @return 문의글과 답변
	 */
	public List<SupportDTO> getAllSupportWithAnswer(int pageNumber, int pageSize) {
		int offset = (pageNumber - 1) * pageSize; // 조회 시작 위치
		return supportMapper.getAllSupportWithAnswer(pageSize, offset);
	}
	
	/**
	 * 총 문의글 개수 조회
	 * @return 총 문의글 개수
	 */
	public int getTotalSupportCount() {
		return supportMapper.getTotalSupportCount();
	}

	/**
	 * 문의글 등록
	 * @param supportDto 등록할 문의글 정보
	 */
    public void submitSupport(SupportDTO supportDto) {
    	supportMapper.insertSupport(supportDto);
    }

    /**
     * 문의 상태 "답변 완료"로 업데이트
     */
    public void updateStatus() {
        supportMapper.updateStatus("답변 완료");
    }
}
