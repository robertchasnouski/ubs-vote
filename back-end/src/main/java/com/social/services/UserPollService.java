package com.social.services;

import com.social.entities.Poll;
import com.social.entities.User;
import com.social.entities.UserPoll;

public interface UserPollService {
    UserPoll save(UserPoll poll);

    UserPoll createVoteForPoll(User user, Poll poll, boolean choice);

    UserPoll changeDecision(User user, Poll poll, boolean choice);

    void voteForPoll(User user, Poll poll, Boolean choice);
}
