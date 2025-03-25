package com.jongyeop.soompyo.user.model;

import java.util.ArrayList;
import java.util.List;

import com.jongyeop.soompyo.diary.model.Diary;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TempUser {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String userId;

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private List<Diary> diaries = new ArrayList<>();

	protected TempUser() {

	}

	public TempUser(String userId, String username) {
		this.userId = userId;
		this.username = username;
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
