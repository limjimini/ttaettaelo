package com.tanine.ttaettaelo.service;

import java.sql.SQLException;
import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tanine.ttaettaelo.dto.MemberDTO;
import com.tanine.ttaettaelo.mapper.MemberMapper;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

/**
 * 회원 관련 서비스를 처리하는 클래스
 * 회원가입, 회원탈퇴, 회원정보 수정, 아이디/비밀번호 찾기와 같은 기능을 제공한다.
 */
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	private final JavaMailSender mailSender;
	
	/**
	 * 로그인 아이디 중복 체크
	 * @param loginId 사용자가 입력한 로그인 아이디
	 * @return 로그인 아이디 중복 여부
	 */
	public boolean isDuplicatedLoginId(String loginId) {
		return memberMapper.countLoginId(loginId) > 0; // 1개라도 있으면 true(중복 아이디O)
	}
	
	/**
	 * 회원가입
	 * @param memberDto 저장할 회원 정보
	 */
	@Transactional
	public void saveMember(MemberDTO memberDto) {		
		// 비밀번호 암호화
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));		
		memberMapper.insertMember(memberDto); // 회원 저장
	}
	
	/**
	 * 회원의 이름과 이메일을 통한 로그인 아이디 찾기
	 * @param name 회원 이름
	 * @param email 회원 이메일 주소
	 * @return 해당 조건에 맞는 회원의 로그인 아이디
	 */
	public String findLoginId(String name, String email) {
		return memberMapper.getLoginIdByNameAndEmail(name, email);
	}
	
	/**
	 * 임시 비밀번호를 생성하여 회원에게 이메일을 보내고
	 * 회원 데이터베이스에 임시 비밀번호로 변경
	 * @param loginId 회원 로그인 아이디
	 * @param email 회원 이메일 주소
	 * @return 임시 비밀번호 메일 전송 여부
	 * @throws MessagingException 이메일 전송 실패시 예외 발생
	 */
	public boolean sendTemporaryPassword(String loginId, String email) throws MessagingException {
		MemberDTO member = memberMapper.getMemberByLoginIdAndEmail(loginId, email); // 로그인 아이디와 이메일로 회원 얻기
		
		if(member == null) { // 회원이 없으면
			return false;
		}
		
		String temporaryPassword = generateTemporaryPassword(); // 임시 비밀번호 생성
		String encryptedPassword = passwordEncoder.encode(temporaryPassword); // 임시 비밀번호 암호화
		
		member.setPassword(encryptedPassword); // 암호화된 임시 비밀번호로 변경
		memberMapper.updatePassword(member); // 데이터베이스에 저장
		
		sendTemporaryPasswordEmail(member.getEmail(), temporaryPassword); // 이메일로 임시 비밀번호 보내기
		
		return true; // 임시 비밀번호 메일 전송 성공
	}
	
	/**
	 * 8자리 임시 비밀번호 랜덤으로 생성
	 * @return 8자리 임시 비밀번호
	 */
	private String generateTemporaryPassword() {
		return UUID.randomUUID().toString().substring(0, 8);
	}
	
	/**
	 * 이메일로 임시 비밀번호 전송
	 * @param toEmail 회원 이메일
	 * @param temporaryPassword 임시 비밀번호
	 * @throws MessagingException 이메일 전송 실패시 예외 발생
	 */
	private void sendTemporaryPasswordEmail(String toEmail, String temporaryPassword) throws MessagingException {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail); // 회원의 이메일 주소 설정
		message.setSubject("때때로 임시 비밀번호 발급"); // 제목 설정
		message.setText("임시 비밀번호: " + temporaryPassword); // 본문 내용 설정
		mailSender.send(message); // 메일 보내기
	}
	
	/**
	 * 회원 정보 변경
	 * @param memberDto 회원 정보
	 * @return 수정된 회원 번호
	 * @throws SQLException SQL 오류 발생시 예외 발생
	 */
	public MemberDTO changeMember(MemberDTO memberDto) throws SQLException {
		try {
			int result = memberMapper.updateMember(memberDto);			
			if(result == 1) { //  변경 성공
				return memberMapper.getMemberById(memberDto.getMemberId());
			} else { // 변경 실패
				throw new RuntimeException("회원 정보 변경 실패");
			}			
		} catch (Exception e) {
		    throw new RuntimeException("SQL 오류 발생: " + e.getMessage());
		}
	}
	
	/**
	 * 회원의 비밀번호 변경
	 * @param loginId 회원의 로그인 아이디
	 * @param password 회원의 변경할 비밀번호
	 * @return 변경 성공 여부
	 */
	public boolean changePassword(String loginId, String password) {
		MemberDTO member = memberMapper.getMemberByLoginId(loginId); // 로그인 아이디로 회원 얻기
		
		if(member == null) { // 회원이 없으면
			return false;
		}
		
		String encryptedPassword = passwordEncoder.encode(password); // 비밀번호 암호화		
		member.setPassword(encryptedPassword); // 암호화된 비밀번호로 변경
		memberMapper.updatePassword(member); // 데이터베이스에 저장
				
		return true;
	}
	
	/**
	 * 회원 탈퇴
	 * @param memberId 탈퇴할 회원 번호
	 * @return 탈퇴 성공 여부
	 */
	public boolean deleteAccount(Long memberId) {
		try {
			int result = memberMapper.deleteMemberById(memberId);
			return result > 0; // 1 이상 삭제 성공
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
