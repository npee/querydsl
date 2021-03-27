package com.npee.querydsl.repository;

import com.npee.querydsl.domain.dto.MemberSearchCondition;
import com.npee.querydsl.domain.dto.MemberTeamDto;
import com.npee.querydsl.domain.entity.Member;
import com.npee.querydsl.domain.entity.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest() throws Exception {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> all = memberJpaRepository.findAll();
        assertThat(all).containsExactly(member);

        List<Member> member1 = memberJpaRepository.findByUsername("member1");
        assertThat(member1).containsExactly(member);
    }

    @Test
    public void basicQuerydslTest() throws Exception {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        List<Member> all = memberJpaRepository.findAll_Querydsl();
        assertThat(all).containsExactly(member);

        List<Member> member1 = memberJpaRepository.findByUsername_Querydsl("member1");
        assertThat(member1).containsExactly(member);
    }

    @Test
    public void searchByBuilder() {
        Team team1 = new Team("teamA");
        Team team2 = new Team("teamB");

        em.persist(team1);
        em.persist(team2);

        Member member1 = new Member("member1", 10, team1);
        Member member2 = new Member("member2", 20, team1);
        Member member3 = new Member("member3", 30, team2);
        Member member4 = new Member("member4", 40, team2);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setUsername("member4");
        condition.setAgeGoe(30);
        List<MemberTeamDto> result = memberJpaRepository.searchByBuilder(condition);

        assertThat(result).extracting("username").containsExactly("member4");
    }

}