package com.jongyeop.soompyo.user.serivce;

import java.util.Optional;

import com.jongyeop.soompyo.user.model.TempUser;

public interface UserService {

	TempUser join(TempUser tempUser);
	Optional<TempUser> findById(Long Id);
	Optional<TempUser> findByUsername(String username);
}
