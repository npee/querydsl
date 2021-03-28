package com.npee.querydsl.repository;

import com.npee.querydsl.domain.dto.MemberSearchCondition;
import com.npee.querydsl.domain.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);
}
