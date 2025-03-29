package com.jongyeop.soompyo.diary.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jongyeop.soompyo.diary.dto.DiaryDto;
import com.jongyeop.soompyo.diary.model.Diary;
import com.jongyeop.soompyo.diary.service.DiaryService;

@RestController
@RequestMapping("/diarys")
public class DiaryController {
	private final DiaryService diaryService;

	public DiaryController(DiaryService diaryService) {
		this.diaryService = diaryService;
	}

	@PostMapping
	public DiaryDto saveDiary(@RequestBody DiaryDto diary) {
		Diary savedDiary = diaryService.save(diary);
		return new DiaryDto(savedDiary.getId(), savedDiary.getOwner().getId(), savedDiary.getTitle(),
			savedDiary.getContent(),
			savedDiary.getCreatedDate(), savedDiary.getModifiedDate());
	}
}
