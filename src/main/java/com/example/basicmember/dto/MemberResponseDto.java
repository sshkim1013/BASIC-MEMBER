package com.example.basicmember.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private final Long id;
    private final String name;
    private final Integer age;
}
