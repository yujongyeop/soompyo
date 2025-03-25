package com.jongyeop.soompyo.diary.dto;

import java.time.LocalDate;

import com.jongyeop.soompyo.user.model.TempUser;

public class DiaryDto {
	private Long id;
	private TempUser owner;
	private String title;
	private String content;
	private LocalDate createdDate;
	private LocalDate modifiedDate;

	public DiaryDto(Long id, TempUser owner, String title, String content, LocalDate createdDate,
		LocalDate modifiedDate) {
		this.id = id;
		this.owner = owner;
		this.title = title;
		this.content = content;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public Long getId() {
		return id;
	}

	public TempUser getOwner() {
		return owner;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}
}
