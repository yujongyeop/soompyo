package com.jongyeop.soompyo.diary.service;

import com.jongyeop.soompyo.diary.dto.DiaryDto;
import com.jongyeop.soompyo.diary.model.Diary;

public interface DiaryService {
	Diary save(DiaryDto diary);
}
