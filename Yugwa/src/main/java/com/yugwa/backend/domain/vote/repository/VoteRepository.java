package com.yugwa.backend.domain.vote.repository;

import com.yugwa.backend.domain.vote.entity.Vote;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {


    List<Vote> findByMemberIdOrderByIdDesc(Long memberId);

    default List<Vote> findAllOrderByIdDesc() {
        return findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    default List<Vote> findAllOrderByTotalCountDesc() {
        return findAll(Sort.by(Sort.Direction.DESC, "totalCount"));
    }
}
