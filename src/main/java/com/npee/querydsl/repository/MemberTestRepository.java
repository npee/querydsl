package com.npee.querydsl.repository;

import com.npee.querydsl.domain.entity.Member;
import com.npee.querydsl.domain.entity.QMember;
import com.npee.querydsl.repository.support.Querydsl4RepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.npee.querydsl.domain.entity.QMember.member;

@Repository
public class MemberTestRepository extends Querydsl4RepositorySupport {

    public MemberTestRepository() {
        super(Member.class);
    }

    public List<Member> basicSelect() {
        return select(member)
                .from(member)
                .fetch();
    }

    public List<Member> basicSelectFrom() {
        return selectFrom(member)
                .fetch();
    }
}
