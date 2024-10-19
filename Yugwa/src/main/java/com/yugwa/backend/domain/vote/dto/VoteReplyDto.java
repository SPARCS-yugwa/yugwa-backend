package com.yugwa.backend.domain.vote.dto;

import com.yugwa.backend.domain.member.entity.Member;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VoteReplyDto {

    private Member member;
    private String text;
    private LocalDateTime regDate;
}
