package com.jongyeop.soompyo.user;

import java.util.ArrayList;
import java.util.List;

import com.jongyeop.soompyo.diary.model.Diary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TempUser {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_uuid")
	private Long id;
	private String username;
	private String userId;
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private List<Diary> diaries = new ArrayList<>();

	protected TempUser() {

	}

	public TempUser(Long id, String username, String userId) {
		this.id = id;
		this.username = username;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getUserId() {
		return userId;
	}
}
