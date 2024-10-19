package com.yugwa.backend.domain.vote.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VoteElementDto {

    private Long id;
    private String title;
    private int count;
}
