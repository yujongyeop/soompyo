package com.jongyeop.soompyo.diary.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jongyeop.soompyo.diary.dto.AddDiaryRequestDto;
import com.jongyeop.soompyo.diary.dto.DiaryResponseDto;
import com.jongyeop.soompyo.diary.service.DiaryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/diarys")
public class DiaryController {
	private final DiaryService diaryService;

	@GetMapping("/{userId}")
	public List<DiaryResponseDto> getDiary(@PathVariable String userId) {
		return diaryService.getDiariesByUserId(userId);
	}

	@PostMapping
	public DiaryResponseDto saveDiary(@RequestBody AddDiaryRequestDto diaryDto) {
		return diaryService.save(diaryDto);
	}
}
