package com.yugwa.backend.domain.vote.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yugwa.backend.domain.vote.dto.VoteElementDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class VoteElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @Column(name = "title")
    private String title;

    @Column(name = "count")
    @ColumnDefault("0")
    private int count;

    @Builder
    public VoteElement(Vote vote, String title, int count) {
        this.vote = vote;
        this.title = title;
    }

    public void countUp() {
        this.count++;
    }

    public void saveVote(Vote vote) {
        this.vote = vote;
    }

    public VoteElementDto fromEntity(VoteElement voteElement) {
        return VoteElementDto.builder()
            .id(voteElement.getId())
            .title(voteElement.getTitle())
            .count(voteElement.getCount())
            .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VoteElement that = (VoteElement) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
