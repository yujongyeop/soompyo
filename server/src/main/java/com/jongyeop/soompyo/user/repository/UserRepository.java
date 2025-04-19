package com.jongyeop.soompyo.user.repository;

import java.util.Optional;

import com.jongyeop.soompyo.user.model.TempUser;

public interface UserRepository {
	TempUser save(TempUser user);

	Optional<TempUser> findById(Long id);

	Optional<TempUser> findByUsername(String username);

	Optional<TempUser> findByUserId(String userId);

}
