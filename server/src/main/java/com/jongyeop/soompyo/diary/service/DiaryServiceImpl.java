package com.jongyeop.soompyo.diary.service;

import org.springframework.stereotype.Service;

import com.jongyeop.soompyo.diary.model.Diary;
import com.jongyeop.soompyo.diary.repository.DiaryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DiaryServiceImpl implements DiaryService {
	private final DiaryRepository diaryRepository;

	public DiaryServiceImpl(DiaryRepository diaryRepository) {
		this.diaryRepository = diaryRepository;
	}

	@Override
	public Diary save(Diary diary) {
		return diaryRepository.save(diary);
	}
}
