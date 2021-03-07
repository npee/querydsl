package com.npee.querydsl;

import com.npee.querydsl.domain.entity.Member;
import com.npee.querydsl.domain.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class ApplicationTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void contextLoads() {
        Member member = new Member(null, 0, null);
        em.persist(member);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember qMember = QMember.member;
        Member result = query.selectFrom(qMember).fetchOne();

        Assertions.assertThat(result).isEqualTo(member);
        Assertions.assertThat(result.getId()).isEqualTo(member.getId());

    }
}