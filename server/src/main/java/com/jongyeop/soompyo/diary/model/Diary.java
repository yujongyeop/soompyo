package com.jongyeop.soompyo.diary.model;

import java.time.LocalDate;

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
	private LocalDate createdDate;
	private LocalDate modifiedDate;

	protected Diary() {
	}

	public Diary(Long id, TempUser owner, String title, String content, LocalDate createdDate, LocalDate modifiedDate) {
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
