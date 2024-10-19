package com.yugwa.backend.domain.vote.repository;

import com.yugwa.backend.domain.vote.entity.VoteWhere;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteWhereRepository extends JpaRepository<VoteWhere, Long> {

    Optional<VoteWhere> findByVoteIdAndMemberId(Long voteId, Long userId);
}
