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
	
	public List<SupportDTO> getAllSupportWithAnswer() {
		return supportMapper.getAllSupportWithAnswer();
	}

    public void submitSupport(SupportDTO supportDto) {
    	supportMapper.insertSupport(supportDto);
    }

    public void updateStatus() {
        supportMapper.updateStatus("답변 완료");
    }
}
