package com.jongyeop.soompyo.user.controller;

import com.jongyeop.soompyo.user.dto.TempUserDto;
import com.jongyeop.soompyo.user.model.TempUser;
import com.jongyeop.soompyo.user.serivce.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @PostMapping("/signup") // 추후 분리 예정(-> auth)
    @ResponseBody
    public TempUserDto signup(@RequestBody TempUserDto requestUserDto) {
        TempUser tempUser = new TempUser(requestUserDto.getUserId(), requestUserDto.getUsername());
        TempUser createdUser = userService.join(tempUser);
        return new TempUserDto(createdUser.getUserId(), createdUser.getUsername());
    }


}
