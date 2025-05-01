package com.jongyeop.soompyo.diary.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jongyeop.soompyo.diary.model.Diary;

import jakarta.annotation.Nonnull;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
	Diary save(@Nonnull Diary diary);

	Optional<Diary> findByIdAndOwnerUserId(Long diaryId, String ownerUserId);

	Optional<List<Diary>> findByOwnerUserIdAndIsDeletedFalse(String userId);
}
