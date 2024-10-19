package com.yugwa.backend.domain.vote.dto.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class VoteSaveRequestDto {

    private String title;

    private LocalDateTime regDate;

    private String[] elements;

    private String category;

}
