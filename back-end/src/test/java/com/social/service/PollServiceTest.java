package com.social.service;

import com.social.controller.PollController;
import com.social.dao.UserRepository;
import com.social.entities.Poll;
import com.social.entities.User;
import com.social.entities.UserPoll;
import com.social.services.PollService;
import com.social.services.PollServiceImpl;
import com.social.util.EStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class PollServiceTest {

    public static final Logger logger = LoggerFactory.getLogger(PollServiceTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave()
    {
        User user = new User();
        user.setUsername("user.user");
        user.setFullName("kamalbberriga");
        entityManager.persist(user);
        entityManager.flush();
        // when
        User testUser = userRepository.findOne(user.getId());
        // then
        assertThat(testUser.getFullName()).isEqualTo(user.getFullName());
    }




}
