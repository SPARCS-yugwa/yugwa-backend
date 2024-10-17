package com.yugwa.backend.domain.member.dto;


import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String email;
    private String nickname;
    private String role;
    private LocalDateTime regDate;
    private Integer isResign;
    private Float expLevel;
}
