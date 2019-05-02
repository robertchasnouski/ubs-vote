package com.social.services;

import com.social.entities.Poll;
import com.social.entities.User;

import java.util.List;

public interface PollService {
    List<Poll> findUserPolls(User user);

    List<Poll> findAll();

    Poll save(Poll poll);

    Poll find(Long id);

}
