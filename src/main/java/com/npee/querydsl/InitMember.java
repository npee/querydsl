package com.npee.querydsl;

import com.npee.querydsl.domain.entity.Member;
import com.npee.querydsl.domain.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init() {
        initMemberService.initMember();
    }

    @ComponentScan
    static class InitMemberService {

        @PersistenceContext
        EntityManager em;

        @Transactional
        public void initMember() {
            Team teamA = new Team("teamA");
            Team teamB = new Team("teamB");
            em.persist(teamA);
            em.persist(teamB);

            for (int i = 0; i < 100; i++) {
                Team selectedTeam = i % 2 == 0 ? teamA : teamB;
                Member member = new Member("member" + i, i, selectedTeam);
                em.persist(member);
            }
        }
    }


}
