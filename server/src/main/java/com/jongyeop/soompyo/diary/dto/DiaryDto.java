package com.jongyeop.soompyo.diary.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jongyeop.soompyo.diary.model.Diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDto {
	private Long id;
	private Long owner;
	private String title;
	private String content;
	private LocalDate targetDate;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;


	public static DiaryDto toDto(Diary entity) {
		return DiaryDto.builder()
			.id(entity.getId())
			.title(entity.getTitle())
			.owner(entity.getOwner().getId())
			.content(entity.getContent())
			.targetDate(entity.getTargetDate())
			.createdDate(entity.getCreatedDate())
			.modifiedDate(entity.getModifiedDate())
			.build();
	}

}