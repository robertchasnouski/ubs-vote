package com.social.repository;

import com.social.dao.UserPollRepository;
import com.social.entities.Poll;
import com.social.entities.User;
import com.social.entities.UserPoll;
import com.social.services.PollService;
import com.social.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class UserPollRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserPollRepository userPollRepository;

    @MockBean
    private PollService pollService;

    @Test
    public void testSave()
    {
        User user = new User();
        user.setUsername("user.user");
        user.setFullName("kamalbberriga");
        entityManager.persist(user);

        Poll poll = new Poll("QuestionTest");
        entityManager.persist(poll);

        UserPoll userPoll = new UserPoll(user,poll,true);

        entityManager.persist(userPoll);

        entityManager.flush();
        // when
        UserPoll userPollInDb = userPollRepository.findAllByUserId(user.getId()).get(0);
        // then
        assertThat(userPollInDb.getId() == userPoll.getId());
    }



}
