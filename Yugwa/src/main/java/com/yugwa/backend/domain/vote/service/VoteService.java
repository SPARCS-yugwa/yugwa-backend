package com.yugwa.backend.domain.vote.service;

import com.yugwa.backend.domain.vote.dto.VoteDto;
import com.yugwa.backend.domain.vote.dto.request.VoteDoRequestDto;
import com.yugwa.backend.domain.vote.dto.request.VoteReplySaveRequestDto;
import com.yugwa.backend.domain.vote.dto.request.VoteSaveRequestDto;
import com.yugwa.backend.domain.vote.dto.response.VoteDetailResponseDto;
import com.yugwa.backend.domain.vote.dto.response.VoteWhereResponseDto;
import java.io.IOException;
import java.util.List;

public interface VoteService {

    void saveVote(VoteSaveRequestDto vote) throws IOException;

    List<VoteDto> getVotes();

    VoteDetailResponseDto getVoteById(Long id);

    List<VoteDto> getVotesByUser();

    List<VoteDto> getVotesOrderByTotalCount();

    void updateVote(VoteReplySaveRequestDto voteReplySaveRequestDto);

    void deleteReply(Long voteId, Long replyId);

    VoteWhereResponseDto voteWhere(Long id);

    VoteDto doVote(VoteDoRequestDto voteDoRequestDto);
}
