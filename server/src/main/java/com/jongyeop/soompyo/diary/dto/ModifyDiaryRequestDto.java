package com.jongyeop.soompyo.diary.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ModifyDiaryRequestDto {
	private String title;
	private String content;
	private LocalDate targetDate;
}
