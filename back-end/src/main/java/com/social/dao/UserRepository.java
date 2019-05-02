package com.social.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     User findOneByUsername(String username);
}
