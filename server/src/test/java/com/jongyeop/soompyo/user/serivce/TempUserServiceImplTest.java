package com.jongyeop.soompyo.user.serivce;


import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.jongyeop.soompyo.user.model.TempUser;
import com.jongyeop.soompyo.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TempUserServiceImplTest {
	private UserService tempUserService;
	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	public void BeforeEach(){
		tempUserService = new TempUserServiceImpl(userRepository);
	}

    @Test
	@Transactional
	@DisplayName("회원가입_성공_테스트")
    public void signupTest(){
        //given
        String userId = "TEST1";
		String userName = "Test_USER";
		TempUser user = new TempUser(userId, userName);

        //when
		TempUser savedUser = tempUserService.join(user);
		Long savedId = savedUser.getId();

		//then
		Optional<TempUser> findUser = tempUserService.findById(savedId);
		if(findUser.isPresent()){
			assertThat(user.getUsername()).isEqualTo(findUser.get().getUsername());
		}else{
			throw new RuntimeException("사용자를 찾을 수 없습니다.");
		}
    }

	@Test
	@Transactional
	@DisplayName("회원_이름_조회_테스트")
	public void findByUsernameTest(){
		//given
		String userId = "TEST1";
		String username = "Test_USER";
		TempUser user = new TempUser(userId, username);
		TempUser savedUser = tempUserService.join(user);

		//when
		Optional<TempUser> findUser = tempUserService.findByUsername(savedUser.getUsername());

		//then
		if(findUser.isPresent()){
			assertThat(savedUser.getId()).isEqualTo(findUser.get().getId());
		}else{
			throw new RuntimeException("사용자를 찾을 수 없습니다.");
		}


	}
}