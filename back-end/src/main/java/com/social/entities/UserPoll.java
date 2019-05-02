package com.social.entities;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_poll")
public class UserPoll implements Serializable {

    @Id
    @GeneratedValue
    @Column(name =  "user_poll_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poll_id")
    private Poll poll;

    private long attempt;

    private boolean favorChoice;

    @CreationTimestamp
    private Date tsCreated;

    public UserPoll() {
    }

    public UserPoll(User user, Poll poll, boolean favorChoice) {
            this.user = user;
            this.poll = poll;
            this.favorChoice = favorChoice;
            this.attempt++;
    }

    public boolean isFavorChoice() {
        return favorChoice;
    }

    public void setFavorChoice(boolean favorChoice) {
        this.favorChoice = favorChoice;
    }

    public Date getTsCreated() {
        return tsCreated;
    }

    public void setTsCreated(Date tsCreated) {
        this.tsCreated = tsCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public long getAttempt() {
        return attempt;
    }

    public void setAttempt(long attempt) {
        this.attempt = attempt;
    }
}
