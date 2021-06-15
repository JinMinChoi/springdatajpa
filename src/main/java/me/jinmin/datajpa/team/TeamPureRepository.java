package me.jinmin.datajpa.team;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TeamPureRepository {

    private final EntityManager em;

    //저장
    public void save(Team team) {
        em.persist(team);
    }

    //삭제
    public void delete(Team team) {
        em.remove(team);
    }

    //전체 조회
    public List<Team> findAll() {
        return em.createQuery("select t from Team t", Team.class)
                .getResultList();
    }

    //단건 조회
    public Optional<Team> findById(Long teamId) {
        return Optional.ofNullable(em.find(Team.class, teamId));
    }

    //카운트
    public long count(){
        return em.createQuery("select count(t) from Team t", Long.class)
                .getSingleResult();
    }
}
