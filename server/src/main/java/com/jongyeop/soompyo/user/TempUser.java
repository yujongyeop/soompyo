package com.jongyeop.soompyo.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TempUser {
	@Id @GeneratedValue
	private Long id;
	private String username;
	private String userId;

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
