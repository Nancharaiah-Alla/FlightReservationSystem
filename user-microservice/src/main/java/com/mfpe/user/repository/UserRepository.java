package com.mfpe.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfpe.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserNameAndPassword(String userName, String password);

}