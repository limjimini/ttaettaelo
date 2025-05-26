package com.tanine.ttaettaelo.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tanine.ttaettaelo.dto.SupportDTO;
import com.tanine.ttaettaelo.mapper.LoginMapper;
import com.tanine.ttaettaelo.mapper.SupportMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupportService {

	private final SupportMapper supportMapper;
	
	public List<SupportDTO> getAllSupportWithAnswer() { // 문의글 + 문의답변 조회
		return supportMapper.getAllSupportWithAnswer();
	}

    public void submitSupport(SupportDTO supportDto) { // 문의글 작성
    	supportMapper.insertSupport(supportDto);
    }

    public void updateStatus() { // 문의 상태 업데이트
        supportMapper.updateStatus("답변 완료");
    }
}
