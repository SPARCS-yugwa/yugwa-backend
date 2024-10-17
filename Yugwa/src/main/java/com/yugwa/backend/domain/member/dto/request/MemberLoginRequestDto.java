package com.yugwa.backend.domain.member.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberLoginRequestDto {

    private String kakaoAccessToken;
}
