package com.jongyeop.soompyo.diary.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AddDiaryRequestDto {
	private String userId;
	private String title;
	private String content;
	private LocalDate targetDate;
	private LocalDateTime createdDate;
}
