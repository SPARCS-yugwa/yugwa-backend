package com.yugwa.backend.domain.vote.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VoteWhereResponseDto {

    private Long userId;
    private Long voteId;
    private Long voteElementId;
}
