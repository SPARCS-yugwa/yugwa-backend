package com.yugwa.backend.domain.paper.service;

import com.yugwa.backend.domain.paper.dto.response.PaperSearchDetailResponseDto;
import com.yugwa.backend.domain.paper.dto.response.PaperSearchResponseDto;
import com.yugwa.backend.domain.paper.entity.Paper;
import com.yugwa.backend.domain.paper.repository.PaperRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PaperServiceImpl implements PaperService {

    private final PaperRepository paperRepository;

    @Override
    public List<PaperSearchResponseDto> searchPaper(String word) {
        List<Paper> papers = paperRepository.searchByTitle(word);

        // 검색된 Paper 엔티티를 PaperSearchResponseDto로 변환하여 반환
        return papers.stream()
            .map(paper -> PaperSearchResponseDto.builder()
                .id(paper.getId())
                .title(paper.getTitle())
                .jrnlNmKo(paper.getJrnlNmKo())
                .year(paper.getYear())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public PaperSearchDetailResponseDto searchPaperDetail(Long paperId) {
        Paper paper = paperRepository.findById(paperId)
            .orElseThrow(() -> new NoSuchElementException("No paper found with ID: " + paperId));

        return PaperSearchDetailResponseDto.builder()
            .id(paper.getId())
            .title(paper.getTitle())
            .summary(paper.getSummary())
            .jrnlNmKo(paper.getJrnlNmKo())
            .year(paper.getYear())
            .build();
    }
}

