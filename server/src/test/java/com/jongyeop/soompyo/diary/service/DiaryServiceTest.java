package com.jongyeop.soompyo.diary.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jongyeop.soompyo.diary.dto.AddDiaryRequestDto;
import com.jongyeop.soompyo.diary.dto.DiaryResponseDto;
import com.jongyeop.soompyo.diary.dto.ModifyDiaryRequestDto;
import com.jongyeop.soompyo.diary.model.Diary;
import com.jongyeop.soompyo.diary.repository.DiaryRepository;
import com.jongyeop.soompyo.user.model.TempUser;
import com.jongyeop.soompyo.user.repository.TempUserRepository;
import com.jongyeop.soompyo.user.serivce.TempUserServiceImpl;

@SpringBootTest
@Transactional
public class DiaryServiceTest {
	private DiaryService diaryService;
	private TempUserServiceImpl tempUserService;
	@Autowired
	private TempUserRepository tempUserRepository;
	@Autowired
	private DiaryRepository diaryRepository;

	@BeforeEach
	public void beforeEach() {
		tempUserService = new TempUserServiceImpl(tempUserRepository);
		diaryService = new DiaryServiceImpl(diaryRepository, tempUserRepository);
		TempUser tempUser = new TempUser("Test", "testName");
		tempUserRepository.saveAndFlush(tempUser); // 테스트를 위해 저장 후 플러시 작업을 수행
	}

	public void preSettingDiary() {
		TempUser findUser = tempUserService.findByUsername("testName")
			.orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
		String title = "Diary Test";
		String content = "This is diary Test";
		AddDiaryRequestDto diaryResponseDto = AddDiaryRequestDto.builder()
			.userId(findUser.getUserId())
			.title(title)
			.content(content)
			.targetDate(LocalDate.of(2025, 3, 30))
			.build();
		diaryService.save(diaryResponseDto);
	}

	@Test
	public void CreateDiaryTest() {
		//given
		String title = "Diary Test";
		String content = "This is diary Test";

		Optional<TempUser> findUser = tempUserService.findByUsername("testName");

		//when
		if (!findUser.isPresent()) {
			throw new RuntimeException("사용자를 찾을 수 없습니다.");
		}
		AddDiaryRequestDto diaryResponseDto = AddDiaryRequestDto.builder()
			.userId(findUser.get().getUserId())
			.title(title)
			.content(content)
			.targetDate(LocalDate.of(2025, 3, 30))
			.build();
		DiaryResponseDto savedDiary = diaryService.save(diaryResponseDto);

		//then
		Assertions.assertThat(savedDiary.getTitle()).isEqualTo(title);
		Assertions.assertThat(savedDiary.getContent()).isEqualTo(content);
	}

	@Test
	@Transactional
	public void RemoveDiaryTest() {
		//given
		TempUser findUser = tempUserService.findByUsername("testName")
			.orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
		String title = "Diary Test";
		String content = "This is diary Test";
		AddDiaryRequestDto diaryResponseDto = AddDiaryRequestDto.builder()
			.userId(findUser.getUserId())
			.title(title)
			.content(content)
			.targetDate(LocalDate.of(2025, 3, 30))
			.build();
		DiaryResponseDto savedDiary = diaryService.save(diaryResponseDto);

		//when
		diaryService.deleteDiaryById(findUser.getUserId(), savedDiary.getId());

		List<DiaryResponseDto> userDiarys = diaryService.getDiariesByUserId(findUser.getUserId());
		DiaryResponseDto deletedDiary = null;
		for (DiaryResponseDto userDiary : userDiarys) {
			if (userDiary.getId().equals(savedDiary.getId())) {
				deletedDiary = userDiary;
			}
		}

		//then
		Assertions.assertThat(deletedDiary).isNull();
	}

	@Test
	@Transactional
	@DisplayName("일기 수정 테스트")
	public void modifyDiaryTest() {
		//given
		preSettingDiary(); // 샘플 일기 주입
		String globalTestUserId = "Test";
		List<Diary> testUserDiaries = diaryRepository.getDiaryByOwner_UserId(globalTestUserId);
		Diary targetDiary = testUserDiaries.get(0);
		String diaryFullUpdateDescription = "Full Change Test";
		ModifyDiaryRequestDto modifyDiaryDto = ModifyDiaryRequestDto.builder()
			.title(diaryFullUpdateDescription)
			.content(diaryFullUpdateDescription)
			.build();

		//when
		DiaryResponseDto modifiedDiary = diaryService.modify(globalTestUserId, targetDiary.getId(), modifyDiaryDto);

		//then
		Assertions.assertThat(modifiedDiary.getTitle()).isEqualTo(diaryFullUpdateDescription);
		Assertions.assertThat(modifiedDiary.getContent()).isEqualTo(diaryFullUpdateDescription);
		Assertions.assertThat(modifiedDiary.getModifiedDate()).isNotNull();
	}

}
