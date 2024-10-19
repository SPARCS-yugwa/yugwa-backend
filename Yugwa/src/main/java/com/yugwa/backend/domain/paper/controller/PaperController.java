package com.yugwa.backend.domain.paper.controller;

import com.yugwa.backend.domain.paper.service.PaperService;
import com.yugwa.backend.global.dto.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/papers")
@RestController
@RequiredArgsConstructor
public class PaperController {

    private final PaperService paperService;

    @GetMapping("/{word}")
    @Operation(summary = "word 로 논문 검색", description = "논문 검색 할 때 사용하는 API")
    public SuccessResponse<?> getPaper(@PathVariable String word) {
        return SuccessResponse.ok(paperService.searchPaper(word));
    }

    @GetMapping("/detail/{paperId}")
    @Operation(summary = "특정 논문 개요까지 상세 조회", description = "논문 상세 조회 할 때 사용하는 API")
    public SuccessResponse<?> getPaperDetail(@PathVariable Long paperId) {
        return SuccessResponse.ok(paperService.searchPaperDetail(paperId));
    }
}
