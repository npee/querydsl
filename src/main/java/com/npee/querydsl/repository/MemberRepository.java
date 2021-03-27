package com.npee.querydsl.repository;

import com.npee.querydsl.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsername(String username);
}