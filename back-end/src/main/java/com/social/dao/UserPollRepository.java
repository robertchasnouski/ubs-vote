package com.social.dao;

import com.social.entities.User;
import com.social.entities.UserPoll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPollRepository extends JpaRepository<UserPoll, Long> {
    List<UserPoll> findAllByUserId(Long userId);

    UserPoll findUserPollByUserIdAndPollId(Long userId, Long pollId);
}
