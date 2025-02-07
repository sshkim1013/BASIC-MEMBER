package com.example.basicmember.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberRequestDto {
    private final String name;
    private final Integer age;
}
