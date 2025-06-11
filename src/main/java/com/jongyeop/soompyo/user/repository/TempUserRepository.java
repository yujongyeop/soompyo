package com.jongyeop.soompyo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jongyeop.soompyo.user.model.TempUser;

@Repository
public interface TempUserRepository extends JpaRepository<TempUser, Long>, UserRepository {

}
