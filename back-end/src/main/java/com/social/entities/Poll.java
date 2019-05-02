package com.social.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Poll")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Poll {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name =  "POLL_ID")
    private Long id;

    private String question;

    @CreationTimestamp
    private Date tsCreated;

    @OneToMany(mappedBy = "poll",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserPoll> userPolls;

    @Transient
    private String status;

    @Transient
    private Boolean userChoice = null;

    @Transient
    private Boolean canVote;

    private long favorVoteCount;
    private long underdogVoteCount;

    public Poll() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean isUserChoice() {
        return userChoice;
    }

    public void setUserChoice(Boolean userChoice) {
        this.userChoice = userChoice;
    }



    public long getFavorVoteCount() {
        return favorVoteCount;
    }

    public void setFavorVoteCount(long favorVoteCount) {
        this.favorVoteCount = favorVoteCount;
    }

    public long getUnderdogVoteCount() {
        return underdogVoteCount;
    }

    public void setUnderdogVoteCount(long underdogVoteCount) {
        this.underdogVoteCount = underdogVoteCount;
    }


    public Set<UserPoll> getUserPolls() {
        return userPolls;
    }

    public void setUserPolls(Set<UserPoll> polls) {
        this.userPolls = polls;
    }

    public void addUserPoll(UserPoll userPoll) {
        this.userPolls.add(userPoll);
    }


    public Poll(String question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getTsCreated() {
        return tsCreated;
    }

    public void setTsCreated(Date tsCreated) {
        this.tsCreated = tsCreated;
    }

    public void setCanVote(boolean canVote) {
        this.canVote = canVote;
    }

    public Boolean getCanVote() {
        return canVote;
    }
}
