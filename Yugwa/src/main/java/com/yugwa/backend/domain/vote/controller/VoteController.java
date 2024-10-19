package com.yugwa.backend.domain.vote.controller;

import com.yugwa.backend.domain.vote.dto.VoteDto;
import com.yugwa.backend.domain.vote.dto.request.VoteDoRequestDto;
import com.yugwa.backend.domain.vote.dto.request.VoteReplySaveRequestDto;
import com.yugwa.backend.domain.vote.dto.request.VoteSaveRequestDto;
import com.yugwa.backend.domain.vote.service.VoteService;
import com.yugwa.backend.global.dto.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/votes")
@RestController
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "")
    @Operation(summary = "투표 생성", description = "투표 생성 API")
    public SuccessResponse<?> save(@RequestBody VoteSaveRequestDto voteSaveRequestDto)
        throws IOException {
        voteService.saveVote(voteSaveRequestDto);
        return SuccessResponse.created();
    }

    @GetMapping("/{voteId}")
    @Operation(summary = "vote_id로 조회", description = "vote_id로 투표 상세 조회 api")
    public SuccessResponse<?> findAll(@PathVariable Long voteId) {
        return SuccessResponse.ok(voteService.getVoteById(voteId));
    }

    @GetMapping("")
    @Operation(summary = "vote 전체 조회", description = "vote 전체 조회 api")
    public SuccessResponse<?> findAll() {
        return SuccessResponse.ok(voteService.getVotes());
    }

    @GetMapping("/my")
    @Operation(summary = "내가 올린 vote", description = "내가 올린 vote 전체 조회 api")
    public SuccessResponse<?> findAllByMemberId() {
        return SuccessResponse.ok(voteService.getVotesByUser());
    }

    @GetMapping("/hot")
    @Operation(summary = "총 투표수 많은 순으로 정렬", description = "총 투표수 많은 순으로 정렬 api")
    public SuccessResponse<?> findAllOrderByTotalCount() {
        return SuccessResponse.ok(voteService.getVotesOrderByTotalCount());
    }

    @PatchMapping("/reply")
    @Operation(summary = "댓글 등록", description = "댓글 등록 api")
    public SuccessResponse<?> saveReply(
        @RequestBody VoteReplySaveRequestDto VoteReplySaveRequestDto) {
        voteService.updateVote(VoteReplySaveRequestDto);
        return SuccessResponse.created();
    }

    @DeleteMapping("/reply/{voteId}/{replyId}")
    @Operation(summary = "본인 댓글 삭제", description = "본인 댓글 삭제 api")
    public SuccessResponse<?> deleteReply(@PathVariable Long voteId, @PathVariable Long replyId) {
        voteService.deleteReply(voteId, replyId);
        return SuccessResponse.deleted();
    }

    @PostMapping("/do")
    @Operation(summary = "투표 하기", description = "투표 하는 api")
    public SuccessResponse<?> doVote(@RequestBody VoteDoRequestDto voteDoRequestDto) {
        VoteDto voteDto = voteService.doVote(voteDoRequestDto);
        return SuccessResponse.ok(Objects.requireNonNullElse(voteDto, "투표 실패 이미 투표했음"));
    }

    @GetMapping("/where/{voteId}")
    @Operation(summary = "특정 투표에 어느 항목에 투표 했는지 가져오기", description = "-1 값이 나오면 투표를 아예 하지 않은것")
    public SuccessResponse<?> voteWhere(@RequestParam Long voteId) {
        return SuccessResponse.ok(voteService.voteWhere(voteId));
    }
}
