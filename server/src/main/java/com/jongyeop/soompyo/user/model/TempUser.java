package com.jongyeop.soompyo.user.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jongyeop.soompyo.diary.model.Diary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "username", unique = true)
	private String username;
	@Column(name = "user_id", unique = true)
	private String userId;

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Diary> diaries = new ArrayList<>();

	public TempUser(String userId, String username) {
		this.userId = userId;
		this.username = username;
	}
}
