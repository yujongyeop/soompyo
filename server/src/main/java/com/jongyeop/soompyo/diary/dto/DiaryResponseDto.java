package com.jongyeop.soompyo.diary.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jongyeop.soompyo.diary.model.Diary;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryResponseDto {
	private Long id;
	private String owner;
	private String title;
	private String content;
	private LocalDate targetDate;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;


	public static DiaryResponseDto toDto(Diary entity) {
		return DiaryResponseDto.builder()
			.id(entity.getId())
			.title(entity.getTitle())
			.owner(entity.getOwner().getUserId())
			.content(entity.getContent())
			.targetDate(entity.getTargetDate())
			.createdDate(entity.getCreatedDate())
			.modifiedDate(entity.getModifiedDate())
			.build();
	}

}