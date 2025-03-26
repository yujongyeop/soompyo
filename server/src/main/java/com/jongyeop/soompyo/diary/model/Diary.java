package com.jongyeop.soompyo.diary.model;

import java.time.LocalDateTime;

import com.jongyeop.soompyo.user.model.TempUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Diary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diary_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private TempUser owner;

	private String title;
	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	protected Diary() {
	}

	public Diary(TempUser owner, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
}
