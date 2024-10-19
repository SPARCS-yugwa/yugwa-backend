package com.yugwa.backend.domain.vote.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VoteDto {

    private Long id;

    private String title;

    private int totalCount;

    private List<VoteElementDto> voteElements;

    private LocalDateTime regDate;

    private String category;

    private int replyCount;

}
