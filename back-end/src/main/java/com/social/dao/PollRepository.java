package com.social.dao;

import com.social.entities.Poll;
import com.social.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {


}
