package com.yugwa.backend.domain.paper.service;

import com.yugwa.backend.domain.paper.dto.response.PaperSearchDetailResponseDto;
import com.yugwa.backend.domain.paper.dto.response.PaperSearchResponseDto;
import java.util.List;

public interface PaperService {

    List<PaperSearchResponseDto> searchPaper(String word);

    PaperSearchDetailResponseDto searchPaperDetail(Long paperId);
}
