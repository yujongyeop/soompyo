package com.jongyeop.soompyo.diary.dto;

import java.time.LocalDateTime;

public class DiaryDto {
	private final Long id;
	private final Long owner;
	private final String title;
	private final String content;
	private final LocalDateTime createdDate;
	private final LocalDateTime modifiedDate;

	public DiaryDto(Long id, Long owner, String title, String content, LocalDateTime createdDate,
		LocalDateTime modifiedDate) {
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

	public Long getOwner() {
		return owner;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
}
