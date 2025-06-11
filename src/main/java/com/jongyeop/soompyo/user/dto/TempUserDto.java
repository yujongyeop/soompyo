package com.jongyeop.soompyo.user.dto;

public class TempUserDto {
    private final String username;
    private final String userId;

    public TempUserDto(String username, String userId) {
        this.username = username;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }
}
