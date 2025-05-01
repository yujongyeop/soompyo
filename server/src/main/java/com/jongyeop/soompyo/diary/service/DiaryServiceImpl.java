package com.jongyeop.soompyo.diary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.jongyeop.soompyo.diary.dto.AddDiaryRequestDto;
import com.jongyeop.soompyo.diary.dto.DiaryResponseDto;
import com.jongyeop.soompyo.diary.model.Diary;
import com.jongyeop.soompyo.diary.repository.DiaryRepository;
import com.jongyeop.soompyo.user.model.TempUser;
import com.jongyeop.soompyo.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class DiaryServiceImpl implements DiaryService {
	private final DiaryRepository diaryRepository;
	private final UserRepository userRepository;

	@Override
	public List<DiaryResponseDto> getDiariesByUserId(String userId) {
		TempUser user = userRepository.findByUserId(userId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 사용자를 찾을 수 없습니다."));

		return user.getDiaries().stream()
			.map(DiaryResponseDto::toDto)
			.toList();
	}

	@Override
	@Transactional
	public DiaryResponseDto save(AddDiaryRequestDto diaryDto) {
		Optional<TempUser> findUser = userRepository.findByUserId(diaryDto.getUserId());
		if (findUser.isPresent()) {
			Diary receivedDiary = Diary.builder()
				.owner(findUser.get())
				.title(diaryDto.getTitle())
				.content(diaryDto.getContent())
				.targetDate(diaryDto.getTargetDate())
				.createdDate(diaryDto.getCreatedDate())
				.build();
			return DiaryResponseDto.toDto(diaryRepository.save(receivedDiary));
		} else {
			throw new RuntimeException("사용자를 찾을 수 없습니다.");
		}
	}

	@Override
	@Transactional
	public ResponseEntity<Void> deleteDiaryById(String userId, Long diaryId) {
		Diary findDiary = diaryRepository.findByIdAndOwnerUserId(diaryId, userId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일기를 찾을 수 없습니다."));
		findDiary.softDelete();
		return ResponseEntity.noContent().build();
	}
}
