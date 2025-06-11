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
import com.jongyeop.soompyo.diary.dto.ModifyDiaryRequestDto;
import com.jongyeop.soompyo.diary.model.Diary;
import com.jongyeop.soompyo.diary.model.vo.DiaryUpdateVO;
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
		List<Diary> diaries = diaryRepository.findByOwnerUserIdAndIsDeletedFalse(userId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 사용자를 찾을 수 없습니다."));

		return diaries.stream()
			.map(DiaryResponseDto::toDto)
			.toList();
	}

	@Override
	public DiaryResponseDto getDiaryByUserId(String userId, String diaryId) {
		Diary diary = diaryRepository.findByIdAndOwnerUserId(Long.parseLong(diaryId), userId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자의 일기를 찾을 수 없습니다."));
		return DiaryResponseDto.toDto(diary);
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

	@Override
	@Transactional
	public DiaryResponseDto modify(String userId, Long diaryId, ModifyDiaryRequestDto dto) {
		Diary findDiary = diaryRepository.findByIdAndOwnerUserId(diaryId, userId).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND, "일기를 찾을 수 없습니다.")
		);
		DiaryUpdateVO updateVO = new DiaryUpdateVO(dto.getTitle(), dto.getContent(), dto.getTargetDate());
		findDiary.updateDiary(updateVO);
		return DiaryResponseDto.toDto(findDiary);
	}
}
