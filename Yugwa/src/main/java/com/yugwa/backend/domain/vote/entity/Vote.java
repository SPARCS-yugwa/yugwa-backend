package com.yugwa.backend.domain.vote.entity;

import static jakarta.persistence.CascadeType.ALL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yugwa.backend.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@Entity
@Getter
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "reg_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime regDate;

    @Column(name = "title")
    private String title;

    @Column(name = "totalCount")
    @ColumnDefault("0")
    private int totalCount;

    @Column(name = "category")
    private String category;

    @Column(name = "replyCount")
    @ColumnDefault("0")
    private int replyCount;

    @OneToMany(mappedBy = "vote", cascade = ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<VoteElement> voteElements;

    @OneToMany(mappedBy = "vote", cascade = ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<VoteReply> voteReplies;

    public void saveVoteElements(List<VoteElement> voteElements) {
        this.voteElements = voteElements;
    }

    public void updateReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public void countUp() {
        this.totalCount++;
    }

    public void minusReplyCount() {
        this.replyCount--;
    }

    @Builder
    public Vote(Long id, Member member, LocalDateTime regDate, String title, String category,
        List<VoteElement> voteElements, List<VoteReply> voteReplies, int replyCount) {
        this.id = id;
        this.member = member;
        this.regDate = regDate;
        this.title = title;
        this.category = category;
        this.voteElements = voteElements;
        this.voteReplies = voteReplies;
        this.replyCount = replyCount;
    }
}
