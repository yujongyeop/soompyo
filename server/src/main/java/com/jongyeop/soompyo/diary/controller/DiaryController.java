package com.jongyeop.soompyo.diary.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jongyeop.soompyo.diary.dto.DiaryDto;
import com.jongyeop.soompyo.diary.model.Diary;
import com.jongyeop.soompyo.diary.service.DiaryService;
import com.jongyeop.soompyo.user.model.TempUser;
import com.jongyeop.soompyo.user.serivce.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/diarys")
public class DiaryController {
	private final DiaryService diaryService;
	private final UserService userService;

	public DiaryController(DiaryService diaryService, UserService userService) {
		this.diaryService = diaryService;
		this.userService = userService;
	}

	@PostMapping
	public DiaryDto saveDiary(@RequestBody DiaryDto diary) {
		Diary savedDiary = null;
		Optional<TempUser> writer = userService.findById(diary.getOwner());
		if (writer.isPresent()) {
			savedDiary = diaryService.save(Diary.toEntity(diary, writer.get()));
		}else{
			throw new RuntimeException("사용자를 찾을 수 없습니다.");
		}
		return DiaryDto.toDto(savedDiary);
	}
}
