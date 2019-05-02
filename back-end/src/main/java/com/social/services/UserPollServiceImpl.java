package com.social.services;

import com.social.dao.PollRepository;
import com.social.dao.UserPollRepository;
import com.social.entities.Poll;
import com.social.entities.User;
import com.social.entities.UserPoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPollServiceImpl implements UserPollService {

    @Autowired
    UserPollRepository userPollRepository;

    @Autowired
    PollRepository pollRepository;

    @Override
    public UserPoll save(UserPoll poll) {
        return userPollRepository.saveAndFlush(poll);
    }

    @Override
    public UserPoll createVoteForPoll(User user, Poll poll, boolean choice) {
            if(choice) {
                poll.setFavorVoteCount(poll.getFavorVoteCount()+1);
            } else {
                poll.setUnderdogVoteCount(poll.getUnderdogVoteCount()+1);
            }

        pollRepository.saveAndFlush(poll);

        UserPoll userPoll = new UserPoll();
        userPoll.setUser(user);
        userPoll.setPoll(poll);
        userPoll.setFavorChoice(choice);

        return  userPollRepository.saveAndFlush(userPoll);
    }

    @Override
    public UserPoll changeDecision(User user, Poll poll, boolean choice) {

        if(choice) {
            poll.setFavorVoteCount(poll.getFavorVoteCount()+1);
            poll.setUnderdogVoteCount(poll.getUnderdogVoteCount()-1);
        } else {
            poll.setFavorVoteCount(poll.getFavorVoteCount()-1);
            poll.setUnderdogVoteCount(poll.getUnderdogVoteCount()+1);
        }

        pollRepository.saveAndFlush(poll);

        UserPoll userPoll = userPollRepository.findUserPollByUserIdAndPollId(user.getId(),poll.getId());
        userPoll.setAttempt(2);
        userPoll.setFavorChoice(choice);

        return  userPollRepository.saveAndFlush(userPoll);
    }

    @Override
    public void voteForPoll(User user, Poll poll, Boolean choice) {
        UserPoll userPoll = userPollRepository.findUserPollByUserIdAndPollId(user.getId(),poll.getId());

        if(userPoll == null) {
            createVoteForPoll(user,poll,choice);
        } else {
            changeDecision(user,poll,choice);
        }
    }


}
