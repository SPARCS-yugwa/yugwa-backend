package com.yugwa.backend.domain.vote.dto.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class VoteReplySaveRequestDto {

    private Long voteId;
    private String text;
    private LocalDateTime regDate;
}
