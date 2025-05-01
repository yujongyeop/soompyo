package com.jongyeop.soompyo.diary.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jongyeop.soompyo.diary.dto.AddDiaryRequestDto;
import com.jongyeop.soompyo.diary.dto.DiaryResponseDto;

public interface DiaryService {
	DiaryResponseDto save(AddDiaryRequestDto dto);

	List<DiaryResponseDto> getDiariesByUserId(String userId);

	ResponseEntity<Void> deleteDiaryById(String userId, Long diaryId);
}
