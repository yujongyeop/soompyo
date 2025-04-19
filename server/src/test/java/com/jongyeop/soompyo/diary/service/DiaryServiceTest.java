package com.jongyeop.soompyo.diary.service;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jongyeop.soompyo.diary.dto.AddDiaryRequestDto;
import com.jongyeop.soompyo.diary.dto.DiaryResponseDto;
import com.jongyeop.soompyo.diary.repository.DiaryRepository;
import com.jongyeop.soompyo.user.model.TempUser;
import com.jongyeop.soompyo.user.repository.TempUserRepository;
import com.jongyeop.soompyo.user.serivce.TempUserServiceImpl;

@DataJpaTest
public class DiaryServiceTest {
	private DiaryService diaryService;
	private TempUserServiceImpl tempUserService;
	@Autowired
	private TempUserRepository tempUserRepository;
	@Autowired
	private DiaryRepository diaryRepository;

	@BeforeEach
	public void beforeEach() {
		tempUserService
			= new TempUserServiceImpl(tempUserRepository);
		diaryService = new DiaryServiceImpl(diaryRepository, tempUserRepository);
	}

	@Test
	public void CreateDiaryTest() {
		//given
		TempUser tempUser = new TempUser("Test", "testName");
		tempUserRepository.saveAndFlush(tempUser); // 테스트를 위해 저장 후 플러시 작업을 수행

		String title = "Diary Test";
		String content = "This is diary Test";

		Optional<TempUser> findUser = tempUserService.findByUsername("testName");

		//when
		if(!findUser.isPresent()) {
			throw new RuntimeException("사용자를 찾을 수 없습니다.");
		}
		AddDiaryRequestDto diaryResponseDto = AddDiaryRequestDto.builder().userId(findUser.get().getUserId()).title(title).content(content).targetDate(
			LocalDate.of(2025, 3, 30)).build();
		DiaryResponseDto savedDiary = diaryService.save(diaryResponseDto);

		//then
		Assertions.assertThat(savedDiary.getTitle()).isEqualTo(title);
		Assertions.assertThat(savedDiary.getContent()).isEqualTo(content);
	}

}
