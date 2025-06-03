package com.jongyeop.soompyo.diary.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jongyeop.soompyo.diary.dto.AddDiaryRequestDto;
import com.jongyeop.soompyo.diary.dto.DiaryResponseDto;
import com.jongyeop.soompyo.diary.dto.ModifyDiaryRequestDto;
import com.jongyeop.soompyo.diary.service.DiaryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users/{userId}/diaries")
public class DiaryController {
	private final DiaryService diaryService;

	@GetMapping
	public List<DiaryResponseDto> getDiary(@PathVariable String userId) {
		return diaryService.getDiariesByUserId(userId);
	}

	@PostMapping
	public DiaryResponseDto saveDiary(@RequestBody AddDiaryRequestDto diaryDto) {
		return diaryService.save(diaryDto);
	}

	@PatchMapping("/{diaryId}")
	public DiaryResponseDto updateDiary(@PathVariable String userId, @PathVariable Long diaryId, @RequestBody ModifyDiaryRequestDto diaryDto) {
		return diaryService.modify(userId, diaryId, diaryDto);
	}

	@DeleteMapping("/{diaryId}")
	public void deleteDiary(@PathVariable String userId, @PathVariable Long diaryId) {
		diaryService.deleteDiaryById(userId, diaryId);
	}
}
