package com.yugwa.backend.domain.vote.service;

import com.yugwa.backend.domain.member.entity.Member;
import com.yugwa.backend.domain.member.repository.MemberRepository;
import com.yugwa.backend.domain.vote.dto.VoteDto;
import com.yugwa.backend.domain.vote.dto.VoteElementDto;
import com.yugwa.backend.domain.vote.dto.VoteReplyDto;
import com.yugwa.backend.domain.vote.dto.request.VoteDoRequestDto;
import com.yugwa.backend.domain.vote.dto.request.VoteReplySaveRequestDto;
import com.yugwa.backend.domain.vote.dto.request.VoteSaveRequestDto;
import com.yugwa.backend.domain.vote.dto.response.VoteDetailResponseDto;
import com.yugwa.backend.domain.vote.dto.response.VoteWhereResponseDto;
import com.yugwa.backend.domain.vote.entity.Vote;
import com.yugwa.backend.domain.vote.entity.VoteElement;
import com.yugwa.backend.domain.vote.entity.VoteReply;
import com.yugwa.backend.domain.vote.entity.VoteWhere;
import com.yugwa.backend.domain.vote.repository.VoteRepository;
import com.yugwa.backend.domain.vote.repository.VoteWhereRepository;
import com.yugwa.backend.global.common.util.MemberInfo;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final MemberRepository memberRepository;
    private final VoteWhereRepository voteWhereRepository;

    @Override
    public void saveVote(VoteSaveRequestDto voteDto) throws IOException {
        Member member = memberRepository.findById(MemberInfo.getUserId())
            .orElseThrow(
                () -> new IllegalArgumentException("Invalid member ID: " + MemberInfo.getUserId()));

        Vote vote = Vote.builder()
            .member(member)
            .regDate(voteDto.getRegDate())
            .category(voteDto.getCategory())
            .title(voteDto.getTitle())
            .build();

        if (voteDto.getElements() != null && voteDto.getElements().length > 0) {
            List<VoteElement> voteElements = Arrays.stream(voteDto.getElements())
                .map(title -> VoteElement.builder().title(title).vote(vote).build())
                .collect(Collectors.toList());
            vote.saveVoteElements(voteElements);
        }

        voteRepository.save(vote);
    }

    @Override
    public List<VoteDto> getVotes() {
        return voteRepository.findAllOrderByIdDesc().stream()
            .map(this::convertToVoteDto)
            .collect(Collectors.toList());
    }

    @Override
    public VoteDetailResponseDto getVoteById(Long id) {
        Vote vote = voteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Vote with ID " + id + " not found"));

        List<VoteElementDto> voteElementDtos = vote.getVoteElements().stream()
            .map(voteElement -> VoteElementDto.builder()
                .id(voteElement.getId())
                .title(voteElement.getTitle())
                .count(voteElement.getCount())
                .build())
            .collect(Collectors.toList());

        List<VoteReplyDto> voteReplyDtos = vote.getVoteReplies().stream()
            .map(voteReply -> VoteReplyDto.builder()
                .text(voteReply.getText())
                .regDate(voteReply.getRegDate())
                .member(voteReply.getMember())
                .build())
            .collect(Collectors.toList());

        return VoteDetailResponseDto.builder()
            .id(vote.getId())
            .totalCount(vote.getTotalCount())
            .voteElements(voteElementDtos)
            .regDate(vote.getRegDate())
            .title(vote.getTitle())
            .category(vote.getCategory())
            .voteReplies(voteReplyDtos)
            .replyCount(vote.getReplyCount())
            .build();
    }

    @Override
    public List<VoteDto> getVotesByUser() {
        return voteRepository.findByMemberIdOrderByIdDesc(MemberInfo.getUserId()).stream()
            .map(this::convertToVoteDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<VoteDto> getVotesOrderByTotalCount() {
        return voteRepository.findAllOrderByTotalCountDesc().stream()
            .map(this::convertToVoteDto)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateVote(VoteReplySaveRequestDto voteReplySaveRequestDto) {
        Vote vote = voteRepository.findById(voteReplySaveRequestDto.getVoteId())
            .orElseThrow(() -> new IllegalArgumentException(
                "Invalid vote ID: " + voteReplySaveRequestDto.getVoteId()));

        Member member = memberRepository.findById(MemberInfo.getUserId())
            .orElseThrow(
                () -> new IllegalArgumentException("Invalid member ID: " + MemberInfo.getUserId()));

        vote.getVoteReplies().add(VoteReply.builder()
            .member(member)
            .text(voteReplySaveRequestDto.getText())
            .regDate(voteReplySaveRequestDto.getRegDate())
            .vote(vote)
            .build());
        vote.updateReplyCount(vote.getReplyCount() + 1);
        voteRepository.save(vote);
    }

    @Override
    public void deleteReply(Long voteId, Long replyId) {
        Vote vote = voteRepository.findById(voteId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Invalid vote ID: " + voteId));

        vote.getVoteReplies().removeIf(voteReply -> voteReply.getId().equals(replyId)
            && Objects.equals(voteReply.getMember().getId(), MemberInfo.getUserId()));
        vote.minusReplyCount();
        voteRepository.save(vote);
    }

    @Override
    public VoteWhereResponseDto voteWhere(Long id) {
        Optional<VoteWhere> voteWhere = voteWhereRepository.findByVoteIdAndMemberId(
            id, MemberInfo.getUserId());
        if (voteWhere.isPresent()) {
            return VoteWhereResponseDto.builder()
                .voteElementId(voteWhere.get().getVoteElementId())
                .voteId(voteWhere.get().getVoteId())
                .userId(MemberInfo.getUserId())
                .build();
        }
        return VoteWhereResponseDto.builder().build();
    }

    @Override
    public VoteDto doVote(VoteDoRequestDto voteDoRequestDto) {

        Optional<VoteWhere> voteWhere = voteWhereRepository.findByVoteIdAndMemberId(
            voteDoRequestDto.getVoteId(), MemberInfo.getUserId());

        if (voteWhere.isPresent()) {
            return null;
        }

        voteWhereRepository.save(
            VoteWhere.builder()
                .voteElementId(voteDoRequestDto.getVoteElementId())
                .voteId(voteDoRequestDto.getVoteId())
                .memberId(MemberInfo.getUserId())
                .build());

        Vote vote = voteRepository.findById(voteDoRequestDto.getVoteId())
            .orElseThrow(() -> new IllegalArgumentException(
                "Invalid vote ID: " + voteDoRequestDto.getVoteId()));

        vote.countUp();

        for (VoteElement voteElement : vote.getVoteElements()) {
            if (voteElement.getId().equals(voteDoRequestDto.getVoteElementId())) {
                voteElement.countUp();
            }
        }

        Member member = vote.getMember();
        member.expLevelUp();

        memberRepository.save(member);
        voteRepository.save(vote);

        return convertToVoteDto(vote);
    }


    private VoteDto convertToVoteDto(Vote vote) {
        List<VoteElementDto> voteElementDtos = vote.getVoteElements().stream()
            .map(voteElement -> VoteElementDto.builder()
                .id(voteElement.getId())
                .title(voteElement.getTitle())
                .count(voteElement.getCount())
                .build())
            .collect(Collectors.toList());

        return VoteDto.builder()
            .id(vote.getId())
            .totalCount(vote.getTotalCount())
            .voteElements(voteElementDtos)
            .regDate(vote.getRegDate())
            .title(vote.getTitle())
            .category(vote.getCategory())
            .replyCount(vote.getReplyCount())
            .build();
    }
}