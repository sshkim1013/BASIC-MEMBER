package com.example.basicmember.controller;

import com.example.basicmember.dto.MemberRequestDto;
import com.example.basicmember.dto.MemberResponseDto;
import com.example.basicmember.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;


    @PostMapping
    public ResponseEntity<MemberResponseDto> saveMember(@RequestBody MemberRequestDto dto) {
        return new ResponseEntity<>(memberService.saveMember(dto), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(
            @PathVariable Long id,
            @RequestBody MemberRequestDto dto
    ) {
        return ResponseEntity.ok(memberService.updateMember(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
