package com.yugwa.backend.domain.vote.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoteWhere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "vote_id")
    private Long voteId;

    @Column(name = "voteElement_id")
    private Long voteElementId;

    @Builder
    public VoteWhere(Long memberId, Long voteId, Long voteElementId) {
        this.memberId = memberId;
        this.voteId = voteId;
        this.voteElementId = voteElementId;
    }
}
