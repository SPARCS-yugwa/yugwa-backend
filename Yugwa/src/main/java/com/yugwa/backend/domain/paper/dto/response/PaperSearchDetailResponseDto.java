package com.yugwa.backend.domain.paper.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaperSearchDetailResponseDto {

    private Long id;
    private String title;
    private String jrnlNmKo;
    private String year;
    private String summary;
}
