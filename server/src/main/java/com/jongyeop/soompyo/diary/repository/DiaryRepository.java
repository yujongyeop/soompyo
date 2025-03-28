package com.jongyeop.soompyo.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jongyeop.soompyo.diary.model.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
	Diary save(Diary diary);
}
