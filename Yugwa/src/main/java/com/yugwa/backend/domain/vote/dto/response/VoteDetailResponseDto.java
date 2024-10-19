package com.yugwa.backend.domain.vote.dto.response;

import com.yugwa.backend.domain.vote.dto.VoteElementDto;
import com.yugwa.backend.domain.vote.dto.VoteReplyDto;
import com.yugwa.backend.domain.vote.entity.VoteReply;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VoteDetailResponseDto {

    private Long id;

    private String title;

    private int totalCount;

    private List<VoteElementDto> voteElements;

    private LocalDateTime regDate;

    private String category;

    private List<VoteReplyDto> voteReplies;

    private int replyCount;
}
