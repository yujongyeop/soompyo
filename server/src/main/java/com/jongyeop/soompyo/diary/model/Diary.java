package com.jongyeop.soompyo.diary.model;

import java.time.LocalDate;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
	private LocalDate targetDate;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

}
