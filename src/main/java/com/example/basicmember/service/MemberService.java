package com.example.basicmember.service;

import com.example.basicmember.dto.MemberRequestDto;
import com.example.basicmember.dto.MemberResponseDto;
import com.example.basicmember.entity.Member;
import com.example.basicmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public MemberResponseDto saveMember(MemberRequestDto dto) {
        Member member = new Member(dto.getName(), dto.getAge());
        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember.getId(), savedMember.getName(), savedMember.getAge());
    }


    @Transactional
    public List<MemberResponseDto> findAllMembers() {
        List<Member> members = memberRepository.findAll();

        List<MemberResponseDto> dtoList = new ArrayList<>();
        for (Member member : members) {
            dtoList.add(new MemberResponseDto(member.getId(), member.getName(), member.getAge()));
        }

        return dtoList;
    }


    @Transactional
    public MemberResponseDto findById(Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 회원을 찾을 수 없습니다. ID = " + id)
        );

        return new MemberResponseDto(findMember.getId(), findMember.getName(), findMember.getAge());
    }


    @Transactional
    public MemberResponseDto updateMember(Long id, MemberRequestDto dto) {
        Member findMember = memberRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 회원을 찾을 수 없습니다. ID = " + id)
        );

        findMember.update(dto.getName(), dto.getAge());

        return new MemberResponseDto(findMember.getId(), findMember.getName(), findMember.getAge());
    }


    @Transactional
    public void deleteMember(Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 회원을 찾을 수 없습니다. ID = " + id)
        );

        memberRepository.deleteById(findMember.getId());
    }
}
