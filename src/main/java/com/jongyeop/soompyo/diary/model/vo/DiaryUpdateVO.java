package com.jongyeop.soompyo.diary.model.vo;

import java.time.LocalDate;

public record DiaryUpdateVO(String title, String content, LocalDate targetDate) {

}
