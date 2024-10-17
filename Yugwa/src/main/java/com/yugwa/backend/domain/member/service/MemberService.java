package com.yugwa.backend.domain.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yugwa.backend.domain.member.dto.request.MemberLoginRequestDto;
import com.yugwa.backend.domain.member.dto.response.MemberLoginResponseDto;

public interface MemberService {

    MemberLoginResponseDto login(MemberLoginRequestDto memberLoginRequestDto);

    String kakaoLogin(String code) throws JsonProcessingException;
}
