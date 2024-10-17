package com.yugwa.backend.domain.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yugwa.backend.domain.member.dto.response.MemberLoginResponseDto;
import com.yugwa.backend.domain.member.dto.request.MemberLoginRequestDto;
import com.yugwa.backend.domain.member.service.MemberService;
import com.yugwa.backend.global.dto.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/kakao")
    @Operation(summary = "카카오 리다이렉트", description = "로그인 할 때 사용하는 API")
    public SuccessResponse<?> kakao(@RequestParam String code) throws JsonProcessingException {
        MemberLoginResponseDto loginResponseDto = memberService.login(
            MemberLoginRequestDto.builder().kakaoAccessToken(memberService.kakaoLogin(code))
                .build());
        return SuccessResponse.ok(loginResponseDto);
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "로그아웃 할 때 사용하는 API")
    public SuccessResponse<?> logout(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        // 블랙리스트에 accessToken 과 refreshToken 을 저장하는 로그아웃 방식

        // 1. 해당하는 토큰을 블랙리스트에 담는다

        // 2. 끝

        return SuccessResponse.ok();
    }
}
