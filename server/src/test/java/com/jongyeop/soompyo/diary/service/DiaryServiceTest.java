package com.jongyeop.soompyo.diary.service;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jongyeop.soompyo.diary.model.Diary;
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
	public void beforeEach(){
		tempUserService
			= new TempUserServiceImpl(tempUserRepository);
		diaryService = new DiaryServiceImpl(diaryRepository);
	}

	@Test
	public void CreateDiaryTest(){
	    //given
		TempUser tempUser = new TempUser("Test", "TestName");
	    tempUserService.join(tempUser);

		String title = "Diary Test";
		String content = "This is diary Test";
		LocalDateTime createdDate = LocalDateTime.now();
		Diary diary = new Diary(tempUser, title, content, createdDate, null);

		//when
		Diary savedDiary = diaryService.save(diary);

		//then
		Assertions.assertThat(savedDiary.getId()).isEqualTo(diary.getId());
		Assertions.assertThat(savedDiary.getTitle()).isEqualTo(title);
		Assertions.assertThat(savedDiary.getContent()).isEqualTo(content);
		Assertions.assertThat(savedDiary.getCreatedDate()).isEqualTo(createdDate);

	}

}
