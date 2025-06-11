package com.jongyeop.soompyo.diary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jongyeop.soompyo.diary.model.Diary;

import io.micrometer.common.lang.NonNullApi;
import jakarta.annotation.Nonnull;

@Repository
@NonNullApi
public interface DiaryRepository extends JpaRepository<Diary, Long> {
	<S extends Diary> S save(@Nonnull S diary);

	Optional<Diary> findByIdAndOwnerUserId(Long diaryId, String ownerUserId);

	Optional<List<Diary>> findByOwnerUserIdAndIsDeletedFalse(String userId);

	List<Diary> getDiaryByOwner_UserId(String ownerUserId);
}
