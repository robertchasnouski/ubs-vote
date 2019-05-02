package com.social.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.social.entities.Poll;
import com.social.entities.User;
import com.social.entities.UserPoll;
import com.social.services.PollService;
import com.social.services.UserPollService;
import com.social.services.UserService;
import com.social.util.CustomErrorType;
import com.social.util.EStatus;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/polls")
public class PollController {
    public static final Logger logger = LoggerFactory.getLogger(PollController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PollService pollService;

    @Autowired
    private UserPollService userPollService;


    //@CrossOrigin
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createPoll(@RequestBody Poll p) {
        logger.info("POST - CREATE POLL");
      //  Poll newPoll =new Poll();
        //newPoll.setQuestion();
        return new ResponseEntity<Poll>(pollService.save(p), HttpStatus.CREATED);
    }

   // @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Poll> allPolls(HttpServletRequest request) {
        List<Poll> polls = new ArrayList<>();
        logger.info("ALL POLS - GET REQUEST");

        Principal principal = request.getUserPrincipal();
        User user = userService.find(principal.getName());
        polls = pollService.findUserPolls(user);

        for (int i = 0; i < polls.size(); i++) {

            logger.info("ID:"+polls.get(i).getId()+" ; FAVOR COUNT:"+polls.get(i).getFavorVoteCount()+";CanVote:"+polls.get(i).getCanVote());
        }
        return polls;
    }



    @RequestMapping(value = "/{pollId}", method = RequestMethod.POST)
    @ResponseBody
    public String userVote(HttpServletRequest request, @PathVariable Long pollId, @RequestBody String body) {
        logger.info("CHANGING POLL - POST REQUEST");
        Principal principal = request.getUserPrincipal();
        Boolean choice = Boolean.parseBoolean((new JSONObject(body)).get("userChoice").toString());

        User user = userService.find(principal.getName());
        Poll poll = pollService.find(pollId);

        userPollService.voteForPoll(user, poll, choice);


        return "success";
    }



    @RequestMapping(value = "/generate-poll", method = RequestMethod.GET)
    @ResponseBody
    public List<Poll> test(HttpServletRequest request) {
        List<Poll> polls = new ArrayList<>();

        Principal principal = request.getUserPrincipal();
        User user = userService.find(principal.getName());
        logger.info(user.getUsername());
        Poll poll1 = new Poll("HERE MUST BE STATUS CLOSED");
        Poll poll2 = new Poll("HERE MUST BE STATUS VOTED");
        Poll poll3 = new Poll("HERE MUST BE STATUS NEW");

        UserPoll userPoll1 = new UserPoll(user,poll1,true);
        UserPoll userPoll2 = new UserPoll(user,poll2, true);
        userPoll1.setAttempt(2);

        pollService.save(poll1);
        pollService.save(poll2);
        pollService.save(poll3);

        userPollService.save(userPoll1);
        userPollService.save(userPoll2);

        polls = pollService.findUserPolls(user);

        return polls;
    }
}
