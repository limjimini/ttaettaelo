package com.tanine.ttaettaelo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanine.ttaettaelo.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
