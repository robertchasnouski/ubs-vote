package com.social.services;

import com.social.controller.AccountController;
import com.social.dao.PollRepository;
import com.social.dao.UserPollRepository;
import com.social.entities.Poll;
import com.social.entities.User;
import com.social.entities.UserPoll;
import com.social.util.EStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollServiceImpl implements PollService {

    public static final Logger logger = LoggerFactory.getLogger(PollServiceImpl.class);

    @Autowired

    PollRepository pollRepository;
    @Autowired
    UserPollRepository userPollRepository;

    @Override
    public List<Poll> findUserPolls(User user) {
        logger.info("FINDING USER: " + user.getUsername()+" polls");
        List<Poll> allPolls = pollRepository.findAll();
        List<UserPoll> pollVotings = userPollRepository.findAllByUserId(user.getId());

        for (int i = 0; i < pollVotings.size(); i++) {
            for (int j = 0; j < allPolls.size(); j++) {
                if(pollVotings.get(i).getPoll().getId() == allPolls.get(j).getId()) {
                    allPolls.get(j).setStatus(pollVotings.get(i).getAttempt() >= 2 ? EStatus.CLOSED.toString() : EStatus.VOTED.toString());
                    if(allPolls.get(j).getStatus().equals(EStatus.CLOSED.toString()))
                        allPolls.get(j).setCanVote(false);
                    else
                        allPolls.get(j).setCanVote(true);
                    allPolls.get(j).setUserChoice(pollVotings.get(i).isFavorChoice());
                }
            }
        }
        for (Poll poll : allPolls) {
            if(poll.getCanVote() == null) {
                poll.setStatus(EStatus.NEW.toString());
                poll.setUserChoice(null);
                poll.setCanVote(true);
            }
        }
        return allPolls;
    }

    @Override
    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    public Poll save(Poll poll) {
        return pollRepository.saveAndFlush(poll);
    }

    @Override
    public Poll find(Long id) {
        return pollRepository.findOne(id);
    }


}
