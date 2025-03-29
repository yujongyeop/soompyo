package com.jongyeop.soompyo.diary.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jongyeop.soompyo.diary.dto.DiaryDto;
import com.jongyeop.soompyo.diary.model.Diary;
import com.jongyeop.soompyo.diary.repository.DiaryRepository;
import com.jongyeop.soompyo.user.model.TempUser;
import com.jongyeop.soompyo.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DiaryServiceImpl implements DiaryService {
	private final DiaryRepository diaryRepository;
	private final UserRepository userRepository;

	public DiaryServiceImpl(DiaryRepository diaryRepository, UserRepository userRepository) {
		this.diaryRepository = diaryRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Diary save(DiaryDto diaryDto) {
		Optional<TempUser> findUser = userRepository.findById(diaryDto.getOwner());
		Diary diary = new Diary(findUser.get(), diaryDto.getTitle(), diaryDto.getContent(), LocalDateTime.now(), null);
		return diaryRepository.save(diary);
	}
}
