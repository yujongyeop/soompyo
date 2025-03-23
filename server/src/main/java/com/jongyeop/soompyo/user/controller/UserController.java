package com.jongyeop.soompyo.user.controller;

import com.jongyeop.soompyo.user.dto.TempUserDto;
import com.jongyeop.soompyo.user.model.TempUser;
import com.jongyeop.soompyo.user.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public TempUserDto signup(@RequestBody TempUserDto requestUserDto) {
        TempUser tempUser = new TempUser(requestUserDto.getUserId(), requestUserDto.getUsername());
        TempUser createdUser = userService.join(tempUser);
        return new TempUserDto(createdUser.getUserId(), createdUser.getUsername());
    }


}
