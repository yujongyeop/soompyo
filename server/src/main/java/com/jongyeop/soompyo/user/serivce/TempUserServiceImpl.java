package com.jongyeop.soompyo.user.serivce;

import java.util.Optional;

import com.jongyeop.soompyo.user.model.TempUser;
import com.jongyeop.soompyo.user.repository.UserRepository;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class TempUserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public TempUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public TempUser join(TempUser tempUser) {
        TempUser user = new TempUser(tempUser.getUserId(), tempUser.getUsername());
        return userRepository.save(user);
    }

    @Override
    public Optional<TempUser> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<TempUser> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
