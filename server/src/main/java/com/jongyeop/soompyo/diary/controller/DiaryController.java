package com.jongyeop.soompyo.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jongyeop.soompyo.diary.dto.DiaryDto;
import com.jongyeop.soompyo.diary.model.Diary;
import com.jongyeop.soompyo.diary.repository.DiaryRepository;

@Controller
@RequestMapping("/diarys")
public class DiaryController {
	private final DiaryRepository diaryRepository;

	public DiaryController(DiaryRepository diaryRepository) {
		this.diaryRepository = diaryRepository;
	}

	@PostMapping
	public DiaryDto saveDiary(Diary diary) {
		Diary savedDiary = diaryRepository.save(diary);
		return new DiaryDto(savedDiary.getId(), savedDiary.getOwner(), savedDiary.getTitle(),
			savedDiary.getContent(),
			savedDiary.getCreatedDate(), savedDiary.getModifiedDate());
	}
}
